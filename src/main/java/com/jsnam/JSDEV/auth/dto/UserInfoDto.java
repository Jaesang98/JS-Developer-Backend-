package com.jsnam.JSDEV.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
public class UserInfoDto {
    private String email;
    private String name;
    private String phone;
    private String loginType;
    private String profile;
    private String providerId;
    private LocalDateTime created;
    private LocalDateTime updated;
    private String deleteYn;
    private String role;
}