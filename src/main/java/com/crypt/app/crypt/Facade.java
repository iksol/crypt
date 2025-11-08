package com.crypt.app.crypt;

import com.crypt.app.crypt.request.MessageRequest;
import com.crypt.app.crypt.request.MessageResponse;
import com.crypt.app.crypt.request.MsgDispRequest;
import com.crypt.app.crypt.request.TopicRequest;
import com.crypt.app.crypt.response.MsgDispResponse;
import com.crypt.app.crypt.response.TopicResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("api")
public class Facade {
    private static final Logger logger = LoggerFactory.getLogger(Facade.class);

    @Autowired
    private TopicService topicService;

    @Autowired
    private MsgService msgService;

    @GetMapping("/hello")
    public String hello() {
        logger.info("Hello from log back");
        return "Hello from App";
    }

    @GetMapping("/topic/{topicName}")
    public String topic(@PathVariable String topicName) {
        logger.info("topic name {}", topicName);
        return "Successfuly created a topic";
    }

    @PostMapping("/topic")
    public TopicResponse createTopic(@RequestBody TopicRequest topic) {
        logger.info("topic name {}", topic);
        return topicService.addTopic(topic.getTopic());
    }

    @PostMapping("/addmsg")
    public MessageResponse receiveMessage(@RequestBody MessageRequest msgRequest) {
        return msgService.addMessage(msgRequest);
    }

    @PostMapping("/displayMsg")
    public MsgDispResponse displayMsgs(@RequestBody MsgDispRequest request) {
        return msgService.displayMessages(request);
    }


}
