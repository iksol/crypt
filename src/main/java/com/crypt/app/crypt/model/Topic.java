package com.crypt.app.crypt.model;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class Topic {
    private String topicId;
    private String topicName;
    private String topicCategory;
    private List<Usr> userList;

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getTopicCategory() {
        return topicCategory;
    }

    public void setTopicCategory(String topicCategory) {
        this.topicCategory = topicCategory;
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
