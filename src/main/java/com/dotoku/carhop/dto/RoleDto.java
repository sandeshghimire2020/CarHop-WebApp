package com.dotoku.carhop.dto;

import lombok.Data;

import java.util.List;

@Data
public class RoleDto {

    private Long userId;
    private List<String> roles;

}
