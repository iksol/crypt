package com.crypt.app.crypt.dao;


import com.crypt.app.crypt.MsgDao;
import com.crypt.app.crypt.model.Msg;
import com.crypt.app.crypt.model.Usr;
import com.crypt.app.crypt.s3.S3service;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

@Repository
@Profile("cloud")
public class MsgS3DaoImpl implements MsgDao {

    @Autowired
    private S3service s3service;

    @Override
    public void saveMsg(Usr user, Msg message) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        s3service.writeJsonToS3(message.getTopic() + "/" + user.getUserId(),mapper.writeValueAsString(message));
    }

    @Override
    public List<Msg> displayMessages(String topicName, String userId) throws IOException {
        List<Msg> msgs = s3service.readJsonFromS3(topicName + "/" + userId, Msg.class);
        return msgs;
    }
}
