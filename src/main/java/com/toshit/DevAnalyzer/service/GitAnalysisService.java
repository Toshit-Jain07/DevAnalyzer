package com.toshit.DevAnalyzer.service;

import com.toshit.DevAnalyzer.dto.GitAnalysisResponse;
import com.toshit.DevAnalyzer.model.GitHubUser;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GitAnalysisService extends BaseAnalyzer<GitAnalysisResponse> {

    private final GitHubService gitHubService;

    public GitAnalysisService(GitHubService gitHubService,
                              AiService aiService) {
        super(aiService);
        this.gitHubService = gitHubService;

    }

    @Override
    public GitAnalysisResponse response(String username) {

        GitHubUser user = gitHubService.getUser(username);
        List<GitHubUser.Repo> repos = gitHubService.getRepo(username);

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