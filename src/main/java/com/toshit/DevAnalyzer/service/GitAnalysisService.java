package com.toshit.DevAnalyzer.service;

import com.toshit.DevAnalyzer.dto.GitAnalysisResponse;
import com.toshit.DevAnalyzer.model.GitHubUser;
import com.toshit.DevAnalyzer.model.Repo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GitAnalysisService {

    private final GitHubService gitHubService;
    private final AiService aiService;

    public GitAnalysisService(GitHubService gitHubService,
                              AiService aiService) {
        this.gitHubService = gitHubService;
        this.aiService = aiService;
    }

    public GitAnalysisResponse analyze(String username) {

        GitHubUser user = gitHubService.getUser(username);
        List<Repo> repos = gitHubService.getRepo(username);

        int totalRepos = repos.size();
        int totalStars = gitHubService.getTotalStars(repos);
        double avgStars = gitHubService.getAverageStars(repos);
        int followers = user.getFollowers();

        String summary = "Repos: " + totalRepos +
                ", Avg Stars: " + avgStars +
                ", Followers: " + followers;

        String critique = aiService.getCritique(summary);

        return new GitAnalysisResponse(
                username,
                totalRepos,
                totalStars,
                avgStars,
                followers,
                critique
        );
    }
}