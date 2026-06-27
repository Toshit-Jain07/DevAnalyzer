package com.toshit.DevAnalyzer.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GitHubUser {

    @JsonProperty("login")
    private String userName;
    @JsonProperty("public_repos")
    private int publicRepos;
    private int followers;

    @Data
    public static class Repo {
        private String name;
        private int stargazers_count;
        private String updated_at;
    }
}
