package com.toshit.DevAnalyzer.service;

import com.toshit.DevAnalyzer.dto.LeetCodeAnalysisResponse;
import com.toshit.DevAnalyzer.model.LeetCodeResponse;
import org.springframework.stereotype.Service;

@Service
public class LeetCodeAnalysisService extends BaseAnalyzer<LeetCodeAnalysisResponse> {

    private final LeetCodeService leetCodeService;


    public LeetCodeAnalysisService(LeetCodeService leetCodeService,
                                   AiService aiService) {
        super((aiService));
        this.leetCodeService = leetCodeService;

    }

    @Override
    public LeetCodeAnalysisResponse response(String username) {

        LeetCodeResponse response =
                leetCodeService.getProfile(username);

        LeetCodeResponse.MatchedUser user =
                response.getData().getMatchedUser();

        int easy = 0;
        int medium = 0;
        int hard = 0;
        int total = 0;

        for (LeetCodeResponse.Submission submission :
                user.getSubmitStats().getAcSubmissionNum()) {

            switch (submission.getDifficulty()) {

                case "Easy" -> easy = submission.getCount();

                case "Medium" -> medium = submission.getCount();

                case "Hard" -> hard = submission.getCount();

                case "All" -> total = submission.getCount();

            }
        }

        String summary = """
                Username: %s
                
                Global Rank: %d
                
                Problems Solved:
                Easy: %d
                Medium: %d
                Hard: %d
                Total: %d
                
                Current Streak: %d days
                
                Badges Earned: %d
                """.formatted(
                user.getUsername(),
                user.getProfile().getRanking(),
                easy,
                medium,
                hard,
                total,
                user.getUserCalendar().getStreak(),
                user.getBadges().size()
        );

        String critique = aiService.getCritique(summary);

        LeetCodeAnalysisResponse result =
                new LeetCodeAnalysisResponse();

        result.setUsername(user.getUsername());
        result.setRanking(user.getProfile().getRanking());
        result.setEasySolved(easy);
        result.setMediumSolved(medium);
        result.setHardSolved(hard);
        result.setTotalSolved(total);
        result.setStreak(user.getUserCalendar().getStreak());
        result.setBadgeCount(user.getBadges().size());
        result.setCritique(critique);

        return result;
    }
}