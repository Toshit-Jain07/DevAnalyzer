package com.toshit.DevAnalyzer.service;


import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class Analyzers {
    private final Map<String,BaseAnalyzer<?>> analyzerMap= new HashMap<>();

    public Analyzers(GitAnalysisService github,LeetCodeAnalysisService leetcode) {
        analyzerMap.put("github", github);
        analyzerMap.put("leetcode", leetcode);
    }

    public BaseAnalyzer<?> getAnalyzer(String platform){
        BaseAnalyzer<?> analyzer = analyzerMap.get(platform.toLowerCase());
        if (analyzer == null) {
            throw new RuntimeException("Unknown platform: " + platform);
        }
        return analyzer;
    }

}
