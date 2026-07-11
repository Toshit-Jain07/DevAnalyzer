package com.toshit.DevAnalyzer.dto;

import com.toshit.DevAnalyzer.model.CFUser;
import lombok.Data;

import java.util.List;

@Data
public class CFResponse {
    private String status;
    private List<CFUser> result;
}
