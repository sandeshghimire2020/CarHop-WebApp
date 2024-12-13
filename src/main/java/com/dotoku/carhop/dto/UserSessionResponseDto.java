package com.dotoku.carhop.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserSessionResponseDto extends HopSessionDto {
    private List<HopRequestDto> hopRequest;
}
