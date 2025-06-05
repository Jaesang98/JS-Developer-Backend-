package com.jsnam.JSDEV.auth.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class SignInDto {
    private String userId;
    private String passWord;
}
