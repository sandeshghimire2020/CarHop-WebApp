package com.dotoku.carhop.dto;

import com.dotoku.carhop.entity.HopSession;
import com.dotoku.carhop.entity.HopUser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalDateTime;


@Data
public class HopRequestDto {

    private Long id;
    private Long hopUserId;
    private String status;
    private Long hopSessionId;

    private LocalDateTime requestTime;
}
