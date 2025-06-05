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

    public Member toEntity(String encodedPassword, String role) {
        return Member.builder()
                .userId(userId)
                .userPassword(encodedPassword)
                .role(role)
                .deleteYn("N")
                .loginType("LOCAL")
                .build();
    }
}
