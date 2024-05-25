package com.example.chat;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ChatService {

    @Value("${ollama.url:http://localhost:11434/api/}")
    public String ollamaUrl;

    public String postResponse(String model, String prompt) {
        RestTemplate restTemplate = new RestTemplate();
        String url = ollamaUrl + "generate";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String requestBody = "{\"model\":\"" + model + "\", \"prompt\":\"" + prompt + "\"}";
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        return restTemplate.postForObject(url, requestEntity, String.class);
    }
}
