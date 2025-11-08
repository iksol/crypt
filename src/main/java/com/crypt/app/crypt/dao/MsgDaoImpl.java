package com.crypt.app.crypt.dao;

import com.crypt.app.crypt.MsgDao;
import com.crypt.app.crypt.misc.Constants;
import com.crypt.app.crypt.model.Msg;
import com.crypt.app.crypt.model.Usr;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@Profile("dev")
public class MsgDaoImpl implements MsgDao {

    public void saveMsg(Usr user, Msg message) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Path mPath = Paths.get(Constants.MSG_FILE_PATH + message.getTopic()+"_"+user.getUserId());
        if(mPath.toFile().exists()) {
            Files.writeString(mPath, "\n"+mapper.writeValueAsString(message), StandardOpenOption.APPEND);
        } else {
            Files.writeString(mPath, mapper.writeValueAsString(message));
        }
    }
    
    public List<Msg> displayMessages(String topicName, String userId) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<Msg> lMsg = new ArrayList<>();
        Path dPath = Paths.get(Constants.MSG_FILE_PATH + topicName + "_" + userId);
        List<String> messageList = Files.readAllLines(dPath);
        messageList.stream().forEach(msg -> {
            try {
                Msg msgLocal = mapper.readValue(msg, Msg.class);
                lMsg.add(msgLocal);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });
        return lMsg;
    }
}
