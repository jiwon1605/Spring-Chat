package com.inu.hackerton.spring.controller;

import com.inu.hackerton.spring.model.ChatMessage;
import com.inu.hackerton.spring.model.User;
import com.inu.hackerton.spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller

@RequestMapping("/chat")
public class ChatController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String getChatHtml() {
        return "chat";
    }

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public ChatMessage handle(ChatMessage message) {
        System.out.println(message.getContent());
        return message;
    }


    @PostMapping("/update-sender")
    public ResponseEntity<?> updateSender(@RequestBody UpdateSenderRequest request) {
        Optional<User> userOptional = userRepository.findByUsername(request.getUsername());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setSender(request.getSender());
            userRepository.save(user); // sender 업데이트 후 저장
            return ResponseEntity.ok("Sender updated successfully");
        }
        return ResponseEntity.badRequest().body("User not found");
    }

    // 클라이언트에서 전달받을 데이터 구조
    public static class UpdateSenderRequest {
        private String username;
        private String sender;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getSender() {
            return sender;
        }

        public void setSender(String sender) {
            this.sender = sender;
        }
    }


}