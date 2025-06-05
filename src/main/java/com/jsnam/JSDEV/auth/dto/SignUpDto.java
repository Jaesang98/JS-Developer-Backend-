package com.jsnam.JSDEV.auth.dto;

import com.jsnam.JSDEV.auth.entity.Member;
import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignUpDto {
    private String userId;
    private String passWord;

    public Member toEntity(String encodedPassword, List<String> roles) {
        return Member.builder()
                .userId(userId)
                .userPassword(encodedPassword)
                .roles(roles)
                .deleteYn("N")
                .loginType("LOCAL")
                .build();
    }
}
