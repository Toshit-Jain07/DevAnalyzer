package com.toshit.DevAnalyzer.model;

import lombok.Data;
import java.util.List;

@Data
public class LeetCodeResponse {

    private DataNode data;

    @Data
    public static class DataNode {
        private MatchedUser matchedUser;
    }

    @Data
    public static class MatchedUser {

        private String username;

        private Profile profile;

        private List<Badge> badges;

        private SubmitStats submitStats;

        private UserCalendar userCalendar;
    }

    @Data
    public static class Profile {

        private int ranking;

        private int reputation;

        private String realName;
    }

    @Data
    public static class Badge {

        private String displayName;
    }

    @Data
    public static class SubmitStats {

        private List<Submission> acSubmissionNum;
    }

    @Data
    public static class Submission {

        private String difficulty;

        private int count;
    }

    @Data
    public static class UserCalendar {

        private int streak;
    }

}