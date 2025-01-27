package com.crypt.app.crypt;

import com.crypt.app.crypt.model.Topic;
import com.crypt.app.crypt.response.TopicResponse;

import java.io.IOException;
import java.util.*;

public interface TopicService {
    public TopicResponse addTopic(Topic topic);

    public void removeTopic(Topic topic);

    public List<Topic> listTopics();

    public Topic getTopic(String topicName) throws IOException;
}
