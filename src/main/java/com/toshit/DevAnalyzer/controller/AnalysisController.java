package com.toshit.DevAnalyzer.controller;

import com.toshit.DevAnalyzer.dto.GitAnalysisResponse;
import com.toshit.DevAnalyzer.service.GitAnalysisService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/analyze")
public class AnalysisController {

    private final GitAnalysisService gitAnalysisService;

    public AnalysisController(GitAnalysisService gitAnalysisService) {
        this.gitAnalysisService = gitAnalysisService;
    }

    @GetMapping("/github/{username}")
    public GitAnalysisResponse analyze(@PathVariable String username) {
        return gitAnalysisService.analyze(username);
    }
}