package com.chat.chatApp.chat;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {


    @MessageMapping("/chat.sendMessage")//** donde llega
    @SendTo("/topic/public") //* A donde lo manda /topic porque lo manda a todos
    public ChatMessage sendMessage (
            @Payload ChatMessage chatMessage
    ){
        return chatMessage;
    }

    @MessageMapping("/chat.addUser")//** donde llega
    @SendTo("/topic/public") //* A donde lo manda
    public ChatMessage addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccesor){
        // ** Add user in WebSocket Session
        headerAccesor.getSessionAttributes().put("username", chatMessage.getSender());
        return  chatMessage;
    }


}
