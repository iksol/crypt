package com.crypt.app.crypt.response;

import com.crypt.app.crypt.model.Topic;
import org.springframework.http.HttpStatus;

public class TopicResponse {

    private Topic topic;
    private String action;
    private HttpStatus status;
    private String response;

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

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
