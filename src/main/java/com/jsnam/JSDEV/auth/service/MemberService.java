package com.jsnam.JSDEV.auth.service;

import com.jsnam.JSDEV.auth.dto.JwtDto;

public interface MemberService {
    JwtDto signIn(String email, String password);
}
