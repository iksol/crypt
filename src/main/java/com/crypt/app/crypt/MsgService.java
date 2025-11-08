package com.crypt.app.crypt;

import com.crypt.app.crypt.request.MessageRequest;
import com.crypt.app.crypt.request.MessageResponse;
import com.crypt.app.crypt.request.MsgDispRequest;
import com.crypt.app.crypt.response.MsgDispResponse;

public interface MsgService {

    MessageResponse addMessage(MessageRequest request);

    MsgDispResponse displayMessages(MsgDispRequest request);

}
