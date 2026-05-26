package com.medimind.medimindbackend.service;

import com.medimind.medimindbackend.dto.ChatRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class AIService {

    @Value("${openrouter.api.key}")
    private String apiKey;

    public String getAIResponse(String userMessage) {

        try {

            RestTemplate restTemplate = new RestTemplate();

            String url = "https://openrouter.ai/api/v1/chat/completions";

            HttpHeaders headers = new HttpHeaders();

            headers.setContentType(MediaType.APPLICATION_JSON);

            headers.setBearerAuth(apiKey);

            Map<String, Object> body = new HashMap<>();

            body.put("model", "openai/gpt-3.5-turbo");

            List<Map<String, String>> messages = new ArrayList<>();

            Map<String, String> userMsg = new HashMap<>();

            userMsg.put("role", "user");

            userMsg.put("content", userMessage);

            messages.add(userMsg);

            body.put("messages", messages);

            HttpEntity<Map<String, Object>> request =
                    new HttpEntity<>(body, headers);

            ResponseEntity<Map> response = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    request,
                    Map.class
            );

            List choices =
                    (List) response.getBody().get("choices");

            Map choice =
                    (Map) choices.get(0);

            Map message =
                    (Map) choice.get("message");

            return message.get("content").toString();

        } catch (Exception e) {

            e.printStackTrace();

            return "AI service unavailable.";
        }
    }
}