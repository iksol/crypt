package com.crypt.app.crypt.response;

import com.crypt.app.crypt.model.Msg;

import java.util.List;

public class MsgDispResponse {
    private List<Msg> msgList;

    public List<Msg> getMsgList() {
        return msgList;
    }

    public void setMsgList(List<Msg> msgList) {
        this.msgList = msgList;
    }
}
