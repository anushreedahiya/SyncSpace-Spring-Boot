package com.syncspace.backend.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Map;

@Service
public class GeminiService {

    @Value("${gemini.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();
    private final String baseUrl = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash-latest:generateContent";

    public String generateResponse(String prompt) {
        // Build request body
        Map<String, Object> requestBody = Map.of(
                "contents", new Object[]{
                        Map.of("parts", new Object[]{
                                Map.of("text", prompt)
                        })
                }
        );

        // Set headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);

        try {
            // Send POST request to Gemini API
            ResponseEntity<Map> response = restTemplate.exchange(
                    baseUrl + "?key=" + apiKey,
                    HttpMethod.POST,
                    request,
                    Map.class
            );

            // Parse response
            if (response.getBody() != null && response.getBody().get("candidates") != null) {
                var candidates = (java.util.List<Map<String, Object>>) response.getBody().get("candidates");
                if (!candidates.isEmpty()) {
                    var content = (Map<String, Object>) candidates.get(0).get("content");
                    var parts = (java.util.List<Map<String, Object>>) content.get("parts");
                    if (!parts.isEmpty()) {
                        return parts.get(0).get("text").toString();
                    }
                }
            }
            return "No response from Gemini API.";

        } catch (Exception e) {
            return "Error calling Gemini API: " + e.getMessage();
        }
    }
}
