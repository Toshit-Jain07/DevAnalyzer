package com.toshit.DevAnalyzer.controller;

import com.toshit.DevAnalyzer.model.GitHubUser;
import com.toshit.DevAnalyzer.service.GitHubService;
import org.springframework.web.bind.annotation.*;

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
}