package com.toshit.DevAnalyzer.model;

import lombok.Data;

@Data
public class Repo {
    private String name;
    private int stargazers_count;
    private String updated_at;
}