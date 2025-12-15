package com.syncspace.backend.controller;

import com.syncspace.backend.service.GeminiService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/gemini")
public class GeminiController {

    private final GeminiService geminiService;

    public GeminiController(GeminiService geminiService) {
        this.geminiService = geminiService;
    }

    @PostMapping("/chat")
    public String chatWithGemini(@RequestBody String prompt) {
        return geminiService.generateResponse(prompt);
    }
}
