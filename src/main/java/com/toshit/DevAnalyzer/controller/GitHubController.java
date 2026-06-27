package com.toshit.DevAnalyzer.controller;

import com.toshit.DevAnalyzer.model.GitHubUser;
import com.toshit.DevAnalyzer.service.GitHubService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/github")
public class GitHubController {

    private final GitHubService gitHubService;

    public GitHubController(GitHubService gitHubService) {
        this.gitHubService = gitHubService;
    }

    @GetMapping("/{username}")
    public GitHubUser getUser(@PathVariable String username) {
        return gitHubService.getUser(username);
    }

    @GetMapping("/{username}/repos")
    public String getRepoStats(@PathVariable String username){
        List<GitHubUser.Repo> repos = gitHubService.getRepo(username);

        int totalRepos = repos.size();
        int totalStars = gitHubService.getTotalStars(repos);
        double avgStars = gitHubService.getAverageStars(repos);

        return "Total Repos:" + totalRepos + "\n Total Stars:" + totalStars
                + "Avg Stars:" + avgStars ;
    }
}