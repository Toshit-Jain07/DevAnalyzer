package com.toshit.DevAnalyzer.service;

import com.toshit.DevAnalyzer.model.LeetCodeResponse;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class LeetCodeService {

    private final RestTemplate restTemplate;

    public LeetCodeService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public LeetCodeResponse getProfile(String username) {

        String url = "https://leetcode.com/graphql";

        String query = """
                query getUserProfile($username: String!) {
                  matchedUser(username: $username) {
                    username
                    profile {
                      ranking
                      reputation
                      realName
                    }
                    badges {
                      displayName
                    }
                    submitStats {
                      acSubmissionNum {
                        difficulty
                        count
                      }
                    }
                    userCalendar {
                      streak
                    }
                  }
                }
                """;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> variables = Map.of(
                "username", username
        );

        Map<String, Object> body = Map.of(
                "query", query,
                "variables", variables
        );

        HttpEntity<Map<String, Object>> entity =
                new HttpEntity<>(body, headers);

        ResponseEntity<LeetCodeResponse> response =
                restTemplate.postForEntity(
                        url,
                        entity,
                        LeetCodeResponse.class
                );


        return response.getBody();
    }
}