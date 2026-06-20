package com.example.spring_ai_ollama;

import com.example.spring_ai_ollama.helper.Helper;
import com.example.spring_ai_ollama.service.ChatService;
import com.example.spring_ai_ollama.service.ChatServiceImpl;
import org.junit.jupiter.api.Test;

import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;
@SpringBootTest
class SpringAiOllamaApplicationTests {

	@Autowired
	private  ChatService chatService;
	@Test
	void saveDataToVectorDatabase(){
		System.out.println("saving data to database");
		this.chatService.saveData(Helper.getData());
		System.out.println("data is saved successfully");
	}

}
