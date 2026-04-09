package com.toshit.DevAnalyzer.service;


import com.toshit.DevAnalyzer.model.GitHubUser;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GitHubService {

    private final RestTemplate restTemplate;

    public GitHubService(RestTemplate restTemplate){
        this.restTemplate=restTemplate;
    }

    public GitHubUser getUser(String username) {
        String url = "https://api.github.com/users/" + username;
        try {
            return restTemplate.getForObject(url, GitHubUser.class);
        }
        catch (Exception e){
            throw new RuntimeException("Failed to fetch GitHub user");
        }
    }
}
