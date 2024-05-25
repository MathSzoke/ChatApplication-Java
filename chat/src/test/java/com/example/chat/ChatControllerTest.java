package com.example.chat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = com.example.chat.ChatController.class)
@AutoConfigureMockMvc
class ChatControllerTest {

    @Autowired
    public MockMvc mockMvc;

    @MockBean
    public ChatService chatService;

    @Test
    public void testChatEndpoint() throws Exception {
        when(chatService.postResponse("llama2", "Hello")).thenReturn("mocked response");

        mockMvc.perform(post("/chat")
                        .param("model", "llama2")
                        .param("prompt", "Hello"))
                .andExpect(status().isOk());
    }
}
