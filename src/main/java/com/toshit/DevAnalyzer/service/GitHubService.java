package com.toshit.DevAnalyzer.service;


import com.toshit.DevAnalyzer.model.GitHubUser;
import com.toshit.DevAnalyzer.model.Repo;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class GitHubService {

    private final RestTemplate restTemplate;

    public GitHubService(RestTemplate restTemplate){
        this.restTemplate=restTemplate;
    }

    public GitHubUser getUser(String username) {
        System.out.println("here");
        String url = "https://api.github.com/users/" + username;
        try {
            return restTemplate.getForObject(url, GitHubUser.class);
        }
        catch (Exception e){

            throw new RuntimeException("Failed to fetch GitHub user \n " + e.getMessage()+"\n");
        }
    }

    public List<Repo> getRepo(String username){
        String url = "https://api.github.com/users/" + username + "/repos";
        try {
            Repo[] repos = restTemplate.getForObject(url, Repo[].class);
            return Arrays.asList(repos);
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch repositories \n " + e.getMessage()+"\n");
        }
    }

    public int getTotalStars(List<Repo> repos) {
        if (repos.isEmpty()) return 0;
        int totalStars = 0;

        for (Repo repo : repos) {
            totalStars += repo.getStargazers_count();
        }

        return totalStars;
    }

    public double getAverageStars(List<Repo> repos) {
        if (repos.isEmpty()) return 0;

        int totalStars = getTotalStars(repos);
        return (double) totalStars / repos.size();
    }

}
