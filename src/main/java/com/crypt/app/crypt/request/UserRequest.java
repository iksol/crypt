package com.crypt.app.crypt.request;

import com.crypt.app.crypt.model.Usr;

import java.util.List;

public class UserRequest {
    String topicId;
    List<Usr> userList;

    public UserRequest() {
    }

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    public List<Usr> getUserList() {
        return userList;
    }

    public void setUserList(List<Usr> userList) {
        this.userList = userList;
    }
}
