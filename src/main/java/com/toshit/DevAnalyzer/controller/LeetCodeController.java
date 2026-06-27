package com.toshit.DevAnalyzer.controller;

import com.toshit.DevAnalyzer.model.LeetCodeResponse;
import com.toshit.DevAnalyzer.service.LeetCodeService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/leetcode")
public class LeetCodeController {

    private final LeetCodeService leetCodeService;

    public LeetCodeController(LeetCodeService leetCodeService) {
        this.leetCodeService = leetCodeService;
    }

    @GetMapping("/{username}")
    public LeetCodeResponse getProfile(@PathVariable String username) {

        return leetCodeService.getProfile(username);

    }
}