package com.jsnam.JSDEV.auth.controller;

import com.jsnam.JSDEV.auth.dto.JwtToken;
import com.jsnam.JSDEV.auth.dto.MemberDto;
import com.jsnam.JSDEV.auth.dto.SignInDto;
import com.jsnam.JSDEV.auth.dto.SignUpDto;
import com.jsnam.JSDEV.auth.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/js-dev/member")
public class MemberController {
    private final MemberService memberService;

    // 로그인
    @PostMapping("/sign-in")
    public JwtToken signIn(@RequestBody SignInDto signInDto) {
        String userId = signInDto.getUserId();
        String passWord = signInDto.getPassWord();
        JwtToken jwtToken = memberService.signIn(userId, passWord);
        return jwtToken;
    }

    //로그아웃
//    @PostMapping("/logout")
//    public  logout() {
//
//        return ;
//    }

    // 회원가입
    @PostMapping("/sign-up")
    public ResponseEntity<MemberDto> signUp(@RequestBody SignUpDto signUpDto) {
        MemberDto memberDto = memberService.signUp(signUpDto);
        return ResponseEntity.ok(memberDto);
    }
}
