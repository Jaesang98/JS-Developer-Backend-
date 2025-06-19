package com.jsnam.JSDEV.auth.service;

import com.jsnam.JSDEV.auth.dto.JwtDto;
import com.jsnam.JSDEV.auth.dto.UserInfoDto;
import com.jsnam.JSDEV.auth.entity.Member;

public interface MemberService {
    // 로그인
    JwtDto signIn(String email, String password);
    
    // 아이디 중복체크
    Boolean checkId(String email);

    //회원가입
    Boolean register(Member request);
}
