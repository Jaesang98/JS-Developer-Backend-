package com.jsnam.JSDEV.auth.dto;

import com.jsnam.JSDEV.auth.entity.Member;
import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberDto {
    private String userId;
    private String userName;
    private String loginType;

    static public MemberDto toDto (Member member) {
        return MemberDto.builder()
                .userId(member.getUserId())
                .userName(member.getUsername())
                .loginType(member.getLoginType())
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
