package com.example.spring_ai_ollama.controller;

import com.example.spring_ai_ollama.service.ChatService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class chatController {

    private final ChatService chatService;

    public chatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping("/chat")
    public ResponseEntity<String> chat(
            @RequestParam("q") String query) {

        return ResponseEntity.ok(
                chatService.chatTemplate(query, "user1")
        );
    }
}
























//package com.example.spring_ai_ollama.controller;
//
//import org.springframework.ai.chat.client.ChatClient;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping
//public class chatController {
//    private final ChatClient chatClient;
//
//    public chatController(ChatClient.Builder builder) {
//        this.chatClient = builder.build();
//    }
//
//    @GetMapping("/chat")
//    public ResponseEntity<String> chat(
//            @RequestParam(value = "q") String query) {
//
//        String response = chatClient
//                .prompt(query)
//                .call()
//                .content();
//
//        return ResponseEntity.ok(response);
//    }
//}
//
