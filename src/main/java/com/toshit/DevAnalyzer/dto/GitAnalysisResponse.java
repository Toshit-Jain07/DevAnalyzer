package com.toshit.DevAnalyzer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GitAnalysisResponse {

    private String username;
    private int totalRepos;
    private int totalStars;
    private double avgStars;
    private int followers;
    private String critique;
}