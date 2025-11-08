package com.crypt.app.crypt.s3;

import com.crypt.app.crypt.model.Msg;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.CollectionUtils;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.core.ResponseInputStream;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.model.NoSuchKeyException;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class S3service {

    @Value("${aws.bucketName}")
    private String bucketName;

    private final S3Client s3Client;

    public S3service(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    public String uploadFile(String bucketName, String key, InputStream inputStream, long contentLength) {
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();

        s3Client.putObject(putObjectRequest, RequestBody.fromInputStream(inputStream, contentLength));
        return "File uploaded successfully to S3: " + key;
    }

    public void writeJsonToS3(String key, String jsonString) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<Msg> msgs = this.readJsonFromS3(key, Msg.class);
        byte[] contentAsBytes;
        ByteArrayInputStream contentsAsInputStream;
        if(CollectionUtils.isEmpty(msgs)) {
            jsonString = "[" + jsonString + "]";
            contentAsBytes = jsonString.getBytes(StandardCharsets.UTF_8);
            contentsAsInputStream = new ByteArrayInputStream(contentAsBytes);
        } else {
            msgs.add(mapper.readValue(jsonString, Msg.class));
            String collect = msgs.stream().map(msg -> {
                try {
                    return mapper.writeValueAsString(msg);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }).collect(Collectors.joining(","));
            //contentAsBytes = convertListToByteArray(msgs);
            collect = "[" + collect + "]";
            contentAsBytes = collect.getBytes(StandardCharsets.UTF_8);
            contentsAsInputStream = new ByteArrayInputStream(contentAsBytes);
        }


        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(contentAsBytes.length);
        metadata.setContentType("application/json");
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .contentLength(Long.valueOf(contentAsBytes.length))
                .contentType("application/json")
                .build();
        RequestBody requestBody = RequestBody.fromInputStream(contentsAsInputStream, contentAsBytes.length);
        s3Client.putObject(putObjectRequest,requestBody);
    }

    public  List<Msg>  readJsonFromS3(String key, Class<Msg> valueType) throws IOException {
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();
        byte[] bytes;
        try {
            ResponseInputStream<GetObjectResponse> ris = s3Client.getObject(getObjectRequest);
            bytes = ris.readAllBytes();
        } catch(NoSuchKeyException e) {
            bytes = new byte[0];
        }
        //byte[] bytes = ris.readAllBytes();
        if(bytes.length > 0) {
            String responseString = new String(bytes);
            System.out.println("\n" + responseString);
            ObjectMapper mapper = new ObjectMapper();
            List<Msg> msgs = mapper.readValue(responseString, new TypeReference<List<Msg>>() {
            });
            return msgs;
        } else {
            return new ArrayList<>();
        }
    }

    public <T> T  readJsonObjectFromS3(String key, Class<T> valueType) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();
        try (ResponseInputStream<GetObjectResponse> s3Object = s3Client.getObject(getObjectRequest)) {
            return objectMapper.readValue(s3Object, valueType);
        }
    }




    public ResponseInputStream<GetObjectResponse> downloadFile(String bucketName, String key) throws IOException {
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();

        return s3Client.getObject(getObjectRequest);
    }

    public static byte[] convertListToByteArray(List<?> list) throws IOException {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(bos)) {
            oos.writeObject(list);
            oos.flush();
            return bos.toByteArray();
        }
    }
}