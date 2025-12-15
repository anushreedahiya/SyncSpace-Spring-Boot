package com.syncspace.backend.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Service
public class AIService {

    private final WebClient geminiWebClient;

    @Value("${gemini.model.pm:models/gemini-1.5-flash-latest}")
    private String projectManagerModel;

    @Value("${gemini.model.worker:models/gemini-1.5-flash-latest}")
    private String workerModel;

    public AIService(WebClient geminiWebClient) {
        this.geminiWebClient = geminiWebClient;
    }

    public Mono<String> callProjectManager(String taskDescription) {
        return generateContent(projectManagerModel, "Split the following task into atomic micro-tasks with clear instructions. Return as a numbered list. Task:" + "\n" + taskDescription);
    }

    public Mono<String> callWorker(String instruction) {
        return generateContent(workerModel, instruction);
    }

    private Mono<String> generateContent(String model, String text) {
        Map<String, Object> body = Map.of(
                "contents", List.of(Map.of(
                        "parts", List.of(Map.of("text", text))
                ))
        );

        String path = "/v1beta/" + model + ":generateContent";

        return geminiWebClient.post()
                .uri(uriBuilder -> uriBuilder.path(path).build())
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(body)
                .retrieve()
                .bodyToMono(Map.class)
                .mapNotNull(map -> {
                    Object candidates = map.get("candidates");
                    if (candidates instanceof List<?> list && !list.isEmpty()) {
                        Object content = ((Map<?, ?>) list.get(0)).get("content");
                        if (content instanceof Map<?, ?> contentMap) {
                            Object parts = contentMap.get("parts");
                            if (parts instanceof List<?> pList && !pList.isEmpty()) {
                                Object textObj = ((Map<?, ?>) pList.get(0)).get("text");
                                return textObj != null ? textObj.toString() : null;
                            }
                        }
                    }
                    return null;
                });
    }
}



