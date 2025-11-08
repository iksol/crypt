package com.crypt.app.crypt.impl;

import com.crypt.app.crypt.MsgDao;
import com.crypt.app.crypt.MsgService;
import com.crypt.app.crypt.TopicDao;
import com.crypt.app.crypt.model.Msg;
import com.crypt.app.crypt.model.Topic;
import com.crypt.app.crypt.model.Usr;
import com.crypt.app.crypt.request.MessageRequest;
import com.crypt.app.crypt.request.MessageResponse;
import com.crypt.app.crypt.request.MsgDispRequest;
import com.crypt.app.crypt.response.MsgDispResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class MsgImpl implements MsgService {

    @Autowired
    private TopicDao topicFileDao;

    @Autowired
    private MsgDao msgDao;

    @Override
    public MessageResponse addMessage(MessageRequest request) {
        MessageResponse response = new MessageResponse();
        Msg msg = request.getMsg();
        String topicName = msg.getTopic();
        Topic topic = null;
        try {
            topic = topicFileDao.getTopic(topicName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if(topic != null) {
            List<Usr> userList = topic.getUserList();
            userList.stream().forEach(user -> {
                try {
                    msgDao.saveMsg(user, msg);
                } catch (IOException e) {
                    response.setMsgResponseTxt("Message not added successfully");
                }
                response.setMsgResponseTxt("Message added successfully");
            });
        }

        return response;
    }

    public MsgDispResponse displayMessages(MsgDispRequest request) {
        MsgDispResponse response = new MsgDispResponse();
        try {
            List<Msg> msgs = msgDao.displayMessages(request.getTopicName(), request.getUserId());
            response.setMsgList(msgs);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return response;
    }
}
