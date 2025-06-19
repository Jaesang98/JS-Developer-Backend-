package com.jsnam.JSDEV.auth.controller;

import com.jsnam.JSDEV.SecurityUtil;
import com.jsnam.JSDEV.auth.dto.JwtDto;
import com.jsnam.JSDEV.auth.dto.UserInfoDto;
import com.jsnam.JSDEV.auth.entity.Member;
import com.jsnam.JSDEV.auth.service.MemberService;
import com.jsnam.JSDEV.util.ApiResponse;
import com.jsnam.JSDEV.util.ResponseUtil;
import io.jsonwebtoken.Jwt;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<JwtDto>> signIn(@RequestBody Member request) {
        String email = request.getEmail();
        String password = request.getPassword();

        JwtDto result = memberService.signIn(email, password);

        if(result != null) {
            return ResponseUtil.success(result);
        }
        else {
            return ResponseUtil.fail("로그인에 실패하였습니다.");
        }
    }

    @GetMapping("/check-id")
        public void checkId(@RequestParam("email") String email) {
        System.out.println(email);
        memberService.checkId(email);

//        if (userInfo.isPresent()) {
//            response.put("message", "아이디가 이미 존재합니다.");
//            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
//        } else {
//            response.put("message", "사용 가능한 아이디입니다.");
//            return ResponseEntity.ok(response);
//        }
    }


//    @PostMapping("/sign-in")
//    public ResponseEntity<?> signIn(@RequestBody Member signInDto, HttpServletResponse response) {
//        String email = signInDto.getEmail();
//        String password = signInDto.getPassword();
//        JwtToken jwtToken = memberService.signIn(email, password);
//
//        Optional<Member> userInfo = memberService.memberInfo(userId);
//        if (userInfo.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                    .body(Map.of("error", "존재하지 않는 사용자입니다."));
//        }
//
//        try {
//            JwtToken jwtToken = memberService.signIn(userId, passWord);
//
//            ResponseCookie cookie = ResponseCookie.from("accessToken", jwtToken.getAccessToken())
//                    .httpOnly(true)
//                    .secure(false)
//                    .path("/")
//                    .maxAge(60 * 60)
//                    .sameSite("Lax")
//                    .build();
//            response.setHeader(HttpHeaders.SET_COOKIE, cookie.toString());
//
//            Map<String, Object> responseBody = new HashMap<>();
//            responseBody.put("message", "로그인 성공");
//            responseBody.put("userInfo", userInfo.get());
//            return ResponseEntity.ok(responseBody);
//
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
//                    .body(Map.of("error", "아이디 또는 비밀번호가 틀렸습니다."));
//        }
//    }


//    // 회원가입
//    @PostMapping("/sign-up")
//    public ResponseEntity<Member> signUp(@RequestBody Member data) {
//        Member memberDto = memberService.signUp(data);
//        return ResponseEntity.ok(memberDto);
//    }
//
//    // 아이디 중복
//    @GetMapping("/duplicate")
//    public ResponseEntity<Map<String, Object>> checkUserIdDuplicate(@RequestParam("userId") String userId) {
//        Optional<MemberDto> userInfo = memberService.memberInfo(userId);
//        Map<String, Object> response = new HashMap<>();
//
//        if (userInfo.isPresent()) {
//            response.put("message", "아이디가 이미 존재합니다.");
//            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
//        } else {
//            response.put("message", "사용 가능한 아이디입니다.");
//            return ResponseEntity.ok(response);
//        }
//    }
//
//    // 회원탈퇴
//    @PatchMapping("/withdraw/{userId}")
//    public ResponseEntity<Map<String, Object>> withdrawUser(@PathVariable("userId") String userId) {
//        Optional<MemberDto> userInfo = memberService.memberWithDraw(userId);
//        Map<String, Object> response = new HashMap<>();
//
//        if (userInfo.isPresent()) {
//            response.put("message", "회원탈퇴가 완료되었습니다.");
//            return ResponseEntity.ok(response);
//        } else {
//            response.put("message", "사용자의 정보가 없습니다.");
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
//        }
//    }
}
