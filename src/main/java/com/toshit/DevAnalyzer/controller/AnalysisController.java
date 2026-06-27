package com.toshit.DevAnalyzer.controller;

import com.toshit.DevAnalyzer.dto.GitAnalysisResponse;
import com.toshit.DevAnalyzer.service.Analyzers;
import com.toshit.DevAnalyzer.service.GitAnalysisService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/analyze")
public class AnalysisController {

    private final Analyzers analyzers;

    public AnalysisController(Analyzers analyzers) {
        this.analyzers=analyzers;
    }

    @GetMapping("/{platform}/{username}")
    public Object analyze(@PathVariable String platform , @PathVariable String username) {
        return analyzers.getAnalyzer(platform).analyze(username);
    }
}