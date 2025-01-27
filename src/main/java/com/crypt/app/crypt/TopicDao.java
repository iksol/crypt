package com.crypt.app.crypt;

import com.crypt.app.crypt.model.Topic;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;

public interface TopicDao {
    public void saveTopic(Topic topic) throws IOException;

    public Topic getTopic(String topicName);
}
