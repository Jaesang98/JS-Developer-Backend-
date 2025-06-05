package com.jsnam.JSDEV.auth.service;

import com.jsnam.JSDEV.DictList.dto.DictListDto;
import com.jsnam.JSDEV.auth.dto.JwtToken;
import com.jsnam.JSDEV.auth.dto.MemberDto;
import com.jsnam.JSDEV.auth.dto.SignUpDto;
import com.jsnam.JSDEV.auth.entity.Member;
import com.jsnam.JSDEV.auth.repository.MemberRepository;
import com.jsnam.JSDEV.auth.token.JwtTokenProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class MemberServiceImpl implements MemberService{
    private final MemberRepository memberRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public JwtToken signIn(String userId, String passWord) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userId, passWord);
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        JwtToken jwtToken = jwtTokenProvider.generateToken(authentication);
        return jwtToken;
    }

    @Transactional
    @Override
    public MemberDto signUp(SignUpDto signUpDto) {
        if (memberRepository.existsById(signUpDto.getUserId())) {
            throw new IllegalArgumentException("이미 사용 중인 사용자 입니다.");
        }

        String encodedPassword = passwordEncoder.encode(signUpDto.getPassWord());
        String role = "USER";
        return MemberDto.toDto(memberRepository.save(signUpDto.toEntity(encodedPassword, role)));
    }

    public Optional<MemberDto> memberInfo (String userId) {
        return memberRepository.findByUserId(userId)
                .map(MemberDto::toDto);
    }
}
