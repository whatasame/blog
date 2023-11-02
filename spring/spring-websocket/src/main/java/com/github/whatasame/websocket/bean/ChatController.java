package com.github.whatasame.websocket.bean;

import com.github.whatasame.websocket.model.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @MessageMapping("/chats")
    @SendTo("/topic/messages")
    public Message send(final Message message) {
        return message;
    }
}
