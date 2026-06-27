package com.toshit.DevAnalyzer.dto;

import lombok.Data;

@Data
public class LeetCodeAnalysisResponse {

    private String username;

    private int ranking;

    private int easySolved;

    private int mediumSolved;

    private int hardSolved;

    private int totalSolved;

    private int streak;

    private int badgeCount;

    private String critique;

}