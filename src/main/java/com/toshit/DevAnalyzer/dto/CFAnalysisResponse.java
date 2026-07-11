package com.toshit.DevAnalyzer.dto;

import lombok.Data;

@Data
public class CFAnalysisResponse {
    private String handle;
    private int friends;
    private int currRating;
    private int maxRating;
    private long totalActive;
    private String critique;
}
