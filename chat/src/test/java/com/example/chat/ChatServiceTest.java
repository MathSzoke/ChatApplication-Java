package com.example.chat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = com.example.chat.ChatService.class)
class ChatServiceTest {

    @Autowired
    public ChatService chatService;

    @Test
    public void testGetResponse() {
        String response = chatService.postResponse("llama2", "Hello");
        assertThat(response).isNotNull();
    }
}
