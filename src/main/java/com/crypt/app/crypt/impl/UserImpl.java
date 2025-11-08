package com.crypt.app.crypt.impl;

import com.crypt.app.crypt.TopicDao;
import com.crypt.app.crypt.TopicService;
import com.crypt.app.crypt.UsrService;
import com.crypt.app.crypt.model.Topic;
import com.crypt.app.crypt.model.Usr;
import com.crypt.app.crypt.request.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class UserImpl implements UsrService {

    @Autowired
    private TopicDao topicDao;

    @Autowired
    private TopicService topicService;

    @Override
    public void registerUser(UserRequest userRequest) {
        String topicId = userRequest.getTopicId();
        Topic topic = null;
        try {
            topic = topicDao.getTopic(topicId);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (Objects.isNull(topic.getUserList())) {
            List<Usr> userList = new ArrayList();
            userList.addAll(userRequest.getUserList());
            topic.setUserList(userList);
        } else {
            List<Usr> userList = topic.getUserList();
            Set<Usr> collect = userList.stream().collect(Collectors.toSet());
            collect.addAll(userRequest.getUserList());
            topic.setUserList(collect.stream().toList());
        }
        topicService.addTopic(topic);
    }

    @Override
    public void removeUser(UserRequest userRequest) {
        String topicId = userRequest.getTopicId();
        Topic topic = null;
        try {
            topic = topicDao.getTopic(topicId);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (Objects.isNull(topic.getUserList())) {
            //TODO
        } else {
            List<Usr> userList = topic.getUserList();
            userList.removeAll(topic.getUserList());
            topic.setUserList(userList);
        }
        topicService.addTopic(topic);
    }
}
