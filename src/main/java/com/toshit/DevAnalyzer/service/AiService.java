package com.toshit.DevAnalyzer.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class AiService {

    @Value("${groq.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate;

    public AiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getCritique(String summary) {

        String url = "https://api.groq.com/openai/v1/chat/completions";

        String prompt = """
        Act like a brutally honest senior tech recruiter who roasts weak developer/cp guy profiles.

        Analyze this developer profile harshly but intelligently.

        Developer Data:
        %s

        Rules:
        - Be sarcastic and critical
        - Point out lack of stars/followers/projects/etc brutally
        - Sound like a real recruiter tired of mediocre resumes
        - Keep it funny but realistic
        - Do NOT hold back
        - Give A 2 line ans and at the end a saying for this kind of people(in For you section) !! 

        Format:
        
        Roast:
        ...

        Final Verdict:
        
        FOR YOU:
        ...
        """.formatted(summary);

        try {

            // Headers
            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(apiKey);
            headers.setContentType(MediaType.APPLICATION_JSON);

            // Request Body
            Map<String, Object> requestBody = Map.of(
                    "model", "openai/gpt-oss-120b",
                    "messages", List.of(
                            Map.of(
                                    "role", "user",
                                    "content", prompt
                            )
                    )
            );

            HttpEntity<Map<String, Object>> entity =
                    new HttpEntity<>(requestBody, headers);

            // API Call
            ResponseEntity<Map> response =
                    restTemplate.postForEntity(
                            url,
                            entity,
                            Map.class
                    );

            // Extract Response
            Map body = response.getBody();

            List choices = (List) body.get("choices");

            Map choice = (Map) choices.get(0);

            Map message = (Map) choice.get("message");

            return message.get("content").toString();

        } catch (Exception e) {
            e.printStackTrace();

            return """
                    UNABLE TO DO THE TASK AT THE MOMENT :( .
                    """;
        }
    }
}