package com.toshit.DevAnalyzer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ErrorExceptionResp {
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;
}
