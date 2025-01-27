package com.crypt.app.crypt.request;

import com.crypt.app.crypt.model.Topic;

public class TopicRequest {

    private Topic topic;
    private String action;

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
