package com.dotoku.carhop.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SessionResponseDto {

    private Long sessionId;
    private LocalDateTime startAt;
    private LocalDateTime expireAt;
    private Long startedBy;
    
}
