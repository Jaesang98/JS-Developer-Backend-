package com.jsnam.JSDEV.auth.dto;

import com.jsnam.JSDEV.auth.entity.Member;
import lombok.*;

import java.util.List;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberDto {
    private String userId;
    private String userName;
    private String loginType;
    private String role;

    static public MemberDto toDto (Member member) {
        return MemberDto.builder()
                .userId(member.getUserId())
                .userName(member.getUsername())
                .loginType(member.getLoginType())
                .role(member.getRole())
                .build();
    }

    public Member toEntity() {
        return Member.builder()
                .userId(userId)
                .userName(userName)
                .deleteYn("N")
                .loginType("LOCAL")
                .build();
    }
}
