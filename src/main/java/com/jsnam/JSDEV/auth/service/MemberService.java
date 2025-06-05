package com.jsnam.JSDEV.auth.service;

import com.jsnam.JSDEV.auth.dto.JwtToken;

public interface MemberService {
    JwtToken signIn(String username, String password);
}
