package com.jsnam.JSDEV.auth.controller;

import com.jsnam.JSDEV.auth.dto.JwtToken;
import com.jsnam.JSDEV.auth.dto.MemberDto;
import com.jsnam.JSDEV.auth.dto.SignInDto;
import com.jsnam.JSDEV.auth.dto.SignUpDto;
import com.jsnam.JSDEV.auth.entity.Member;
import com.jsnam.JSDEV.auth.service.MemberService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/js-dev/member")
public class MemberController {
    private final MemberService memberService;

    // 로그인
    @PostMapping("/sign-in")
    public ResponseEntity<?> signIn(@RequestBody SignInDto signInDto, HttpServletResponse response) {
        String userId = signInDto.getUserId();
        String passWord = signInDto.getPassWord();

        Optional<MemberDto> userInfo = memberService.memberInfo(userId);
        if (userInfo.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "존재하지 않는 사용자입니다."));
        }

        try {
            JwtToken jwtToken = memberService.signIn(userId, passWord);

            ResponseCookie cookie = ResponseCookie.from("accessToken", jwtToken.getAccessToken())
                    .httpOnly(true)
                    .secure(false)
                    .path("/")
                    .maxAge(60 * 60)
                    .sameSite("Lax")
                    .build();
            response.setHeader(HttpHeaders.SET_COOKIE, cookie.toString());

            Map<String, Object> responseBody = new HashMap<>();
            responseBody.put("message", "로그인 성공");
            responseBody.put("userInfo", userInfo.get());
            return ResponseEntity.ok(responseBody);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "아이디 또는 비밀번호가 틀렸습니다."));
        }
    }


    // 회원가입
    @PostMapping("/sign-up")
    public ResponseEntity<MemberDto> signUp(@RequestBody SignUpDto signUpDto) {
        MemberDto memberDto = memberService.signUp(signUpDto);
        return ResponseEntity.ok(memberDto);
    }
}
