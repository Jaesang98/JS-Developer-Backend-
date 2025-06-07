package com.jsnam.JSDEV.auth.service;

import com.jsnam.JSDEV.auth.dto.JwtToken;
import com.jsnam.JSDEV.auth.dto.MemberDto;
import com.jsnam.JSDEV.auth.dto.SignUpDto;

import java.util.Optional;

public interface MemberService {
    JwtToken signIn(String userId, String passWord);
    MemberDto signUp(SignUpDto signUpDto);
    Optional<MemberDto> memberInfo(String userId);
    Optional<MemberDto> memberWithDraw(String userId);
}
