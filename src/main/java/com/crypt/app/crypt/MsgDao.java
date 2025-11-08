package com.crypt.app.crypt;

import com.crypt.app.crypt.model.Msg;
import com.crypt.app.crypt.model.Usr;

import java.io.IOException;
import java.util.List;

public interface MsgDao {
    void saveMsg(Usr user, Msg message) throws IOException;

    List<Msg> displayMessages(String topicName, String userId) throws IOException;
}
