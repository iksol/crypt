package com.crypt.app.crypt.dao;

import com.crypt.app.crypt.TopicDao;
import com.crypt.app.crypt.model.Topic;
import com.crypt.app.crypt.s3.S3service;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.io.IOException;

@Repository
@Profile("cloud")
public class TopicS3DaoImpl implements TopicDao {

    @Autowired
    private S3service s3service;

    private static final String topicPrefix = "topic";

    @Override
    public void saveTopic(Topic topic) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        s3service.writeJsonToS3(topicPrefix + "/" + topic.getTopicName(),mapper.writeValueAsString(topic));
    }

    @Override
    public Topic getTopic(String topicName) throws IOException {
        return s3service.readJsonObjectFromS3(topicPrefix + "/" + topicName  ,Topic.class);
    }
}
