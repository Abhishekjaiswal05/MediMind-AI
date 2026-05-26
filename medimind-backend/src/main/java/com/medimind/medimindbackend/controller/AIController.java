package com.medimind.medimindbackend.controller;

import com.medimind.medimindbackend.dto.ChatRequest;
import com.medimind.medimindbackend.service.AIService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai")
@CrossOrigin("*")
public class AIController {

    private final AIService aiService;

    public AIController(AIService aiService) {
        this.aiService = aiService;
    }

    @PostMapping("/chat")
    public String chat(@RequestBody ChatRequest request) {

        return aiService.getAIResponse(
                request.getMessage()
        );
    }
}