package com.example.chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {
    @Autowired
    public ChatService chatService;

    @PostMapping("/chat")
    public String chat(@RequestParam String model, @RequestParam String prompt) {
        return chatService.postResponse(model, prompt);
    }
}
