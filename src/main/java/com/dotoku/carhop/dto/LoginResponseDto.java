package com.dotoku.carhop.dto;

import lombok.Data;

@Data
public class LoginResponseDto {
    private String token;
    private long userId;
}
