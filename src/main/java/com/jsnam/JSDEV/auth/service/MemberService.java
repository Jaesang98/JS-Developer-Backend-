package com.jsnam.JSDEV.auth.service;

import com.jsnam.JSDEV.auth.dto.JwtToken;
import com.jsnam.JSDEV.auth.dto.MemberDto;
import com.jsnam.JSDEV.auth.dto.SignUpDto;

public interface MemberService {
    JwtToken signIn(String userId, String passWord);
    MemberDto signUp(SignUpDto signUpDto);
}
