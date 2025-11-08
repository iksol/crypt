package com.crypt.app.crypt.dao;

import com.crypt.app.crypt.TopicDao;
import com.crypt.app.crypt.misc.Constants;
import com.crypt.app.crypt.model.Topic;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Repository
@Profile("dev")
public class TopicFileDaoImpl implements TopicDao {
    @Override
    public void saveTopic(Topic topic) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        Path pTopic = Paths.get(Constants.TOPIC_FILE_PATH+topic.getTopicName());
        Files.writeString(pTopic, mapper.writeValueAsString(topic));
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
