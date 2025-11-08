package com.crypt.app.crypt.impl;

import com.crypt.app.crypt.TopicDao;
import com.crypt.app.crypt.TopicService;
import com.crypt.app.crypt.misc.Constants;
import com.crypt.app.crypt.model.Topic;
import com.crypt.app.crypt.response.TopicResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
public class TopicImpl implements TopicService {

    @Autowired
    private TopicDao topicDao;

    @Override
    public TopicResponse addTopic(Topic topic) {
        TopicResponse response = null;
        Topic topicExists = null;
        try {
            topicExists = getTopic(topic.getTopicName());
            if(topicExists != null) {
                topicDao.saveTopic(topic);
                response = new TopicResponse();
                response.setTopic(topic);
                response.setResponse("Topic Updated");
                response.setAction("ADD");
                response.setStatus(HttpStatus.OK);
            } else {
                topicDao.saveTopic(topic);
                response = new TopicResponse();
                response.setTopic(topic);
                response.setResponse("Topic Added Successfully!");
                response.setAction("ADD");
                response.setStatus(HttpStatus.OK);
            }
        } catch (IOException e) {
            response = new TopicResponse();
            response.setTopic(topic);
            response.setResponse("Topic Add Failed");
            response.setAction("ADD");
            response.setStatus(HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    @Override
    public void removeTopic(Topic topic) {

    }

    @Override
    public List<Topic> listTopics() {
        return null;
    }

    @Override
    public Topic getTopic(String topicName) throws IOException {
        Path pTopic = Paths.get(Constants.TOPIC_FILE_PATH+topicName);
        ObjectMapper mapper = new ObjectMapper();
        if(pTopic.toFile().isFile()) {
            Topic topic = mapper.readValue(Files.readString(pTopic), Topic.class);
            return topic;
        } else {
            return null;
        }
    }
}
