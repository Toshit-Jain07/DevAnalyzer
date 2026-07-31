package com.toshit.DevAnalyzer.model;

import lombok.Data;

import java.util.List;

@Data
public class CFResponse {
    private String status;
    private List<CFUser> result;
}
