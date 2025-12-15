package com.syncspace.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {
    org.springframework.ai.model.vertexai.autoconfigure.gemini.VertexAiGeminiChatAutoConfiguration.class
})
public class SyncSpaceBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(SyncSpaceBackendApplication.class, args);
	}

}
