package com.toshit.DevAnalyzer.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CFUser {

    private String handle;


    @JsonProperty("friendOfCount")
    private int friends;

    @JsonProperty("rating")
    private int currRating;
    private int maxRating;



    @JsonProperty("registrationTimeSeconds")
    private long registerTime;
    @JsonProperty("lastOnlineTimeSeconds")
    private long lastTime;
}
