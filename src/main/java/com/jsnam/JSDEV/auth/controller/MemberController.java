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
    
    //아이디 중복체크
    @GetMapping("/check-id")
        public ResponseEntity<ApiResponse<String>> checkId(@RequestParam("email") String email) {
        Boolean result = memberService.checkId(email);

        if(result) {
            return ResponseUtil.success("생성 가능한 아이디 입니다.");
        }
        else {
            return ResponseUtil.fail("현재 존재하는 아이디 입니다.");
        }
    }


    // 회원가입
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<String>> register(@RequestBody Member request) {
        Boolean result = memberService.register(request);

        if(result) {
            return ResponseUtil.success("회원가입에 성공하였습니다.");
        }
        else {
            return ResponseUtil.fail("회원가입에 실패했습니다..");
        }
    }

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
