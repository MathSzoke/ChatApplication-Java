# Chat Service Project

## Visão Geral

Este projeto é uma aplicação Spring Boot que fornece uma API RESTful para interagir com um serviço de chat AI. A aplicação se comunica com a API da Ollama, enviando prompts para um modelo de AI generativo e recebendo respostas inteligentes.

## Estrutura do Projeto

O projeto segue a estrutura padrão de diretórios Maven:

	- src
	  - main
	    - java
	      - com.example.chat
	        - ChatController.java
	        - ChatService.java
	      - ChatServiceApplication.java
	    - resources
	  - test
	    (Contém a estrutura padrão de testes)
     
## Arquivos de Código

1. ChatServiceApplication.java

Ponto de entrada da aplicação Spring Boot.

	import org.springframework.boot.SpringApplication;
	import org.springframework.boot.autoconfigure.SpringBootApplication;
	import org.springframework.context.annotation.ComponentScan;

	@SpringBootApplication
	@ComponentScan(basePackages = "com.example.chat")
	public class ChatServiceApplication {

		public static void main(String[] args) {
			SpringApplication.run(ChatServiceApplication.class, args);
		}
	}

3. ChatController.java

Controlador REST que lida com as requisições HTTP.

	package com.example.chat;
	
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.web.bind.annotation.*;
	
	@RestController
	public class ChatController {

	    @Autowired
	    public ChatService chatService;
	
	    @GetMapping("/chat")
	    public String chat(@RequestParam String model, @RequestParam String prompt) {
	        return chatService.postResponse(model, prompt);
	    }
	}
 
## Endpoints:
GET /chat: Recebe os parâmetros model e prompt e retorna uma resposta gerada pelo serviço de chat.

3. ChatService.java

Serviço que lida com a lógica de comunicação com a API da Ollama.

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

## Configuração

application.properties: Arquivo para configurar a porta da API.

	spring.application.name=Chat Application
	server.port=8080

## Testes

O diretório src/test contém a estrutura padrão para testes unitários e de integração. A aplicação pode ser testada usando o framework de testes do Spring Boot para garantir que as camadas de controlador e serviço funcionem conforme o esperado.

## Executando a Aplicação

Para executar a aplicação, use o seguinte comando:

	mvn spring-boot:run
Ou

Clique com o botão direito do mouse na classe "ChatServiceApplication", em seguida, clique em "Run ChatServiceApplication.main()" 
 
A aplicação estará disponível em http://localhost:8080.

# Conclusão
Este projeto fornece uma estrutura simples e eficaz para construir um serviço de chat usando Spring Boot e uma API de AI generativa. Seguindo esta arquitetura, os desenvolvedores podem facilmente estender e manter a aplicação, garantindo uma separação clara de responsabilidades e aproveitando os recursos poderosos do Spring Boot.
