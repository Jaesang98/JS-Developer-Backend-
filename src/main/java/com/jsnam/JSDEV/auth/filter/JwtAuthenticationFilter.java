package com.jsnam.JSDEV.auth.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jsnam.JSDEV.auth.dto.JwtDto;
import com.jsnam.JSDEV.auth.entity.Member;
import com.jsnam.JSDEV.auth.repository.MemberRepository;
import com.jsnam.JSDEV.auth.token.JwtTokenProvider;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilterBean {
    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final ObjectMapper objectMapper = new ObjectMapper();

    // JWT 검증을 건너뛸 경로들
    private static final String[] EXCLUDE_PATHS = {
            "/api/js-dev/member/login",
            "/api/js-dev/member/check-id",
            "/api/js-dev/member/register",
    };


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        if (shouldSkipFilter(httpRequest)) {
            log.debug("JWT 검증 건너뜀: {}", httpRequest.getRequestURI());
            chain.doFilter(request, response);
            return;
        }

        // 1. Request Header에서 JWT 토큰 추출
        String accessToken = resolveToken(httpRequest);

        // 2. validateToken으로 토큰 유효성 검사
        if (StringUtils.hasText(accessToken)) {
            if (jwtTokenProvider.validateToken(accessToken)) {
                // accessToken이 유효한 경우
//                Authentication authentication = jwtTokenProvider.getAuthentication(accessToken);
//                SecurityContextHolder.getContext().setAuthentication(authentication);

                // 1. accessToken에서 email 추출
                String email = jwtTokenProvider.getEmailFromToken(accessToken);

                // 2. DB에서 사용자 정보 조회
                Member member = memberRepository.findByEmailAndDeleteYn(email, "N")
                        .orElseThrow(() -> new RuntimeException("사용자 없음"));

                // 3. Member → Authentication 수동 생성
                Authentication authentication = new UsernamePasswordAuthenticationToken(
                        member, null, member.getAuthorities()
                );

                // 4. SecurityContext에 설정
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            else if (jwtTokenProvider.isTokenExpired(accessToken)) {
                // accessToken이 만료되었지만, 형식은 맞는 경우 → refreshToken 검사
                String refreshToken = httpRequest.getHeader("refreshToken");

                if (StringUtils.hasText(refreshToken) && jwtTokenProvider.validateToken(refreshToken)) {;
                    // 1. refreshToken에서 email 추출
                    String email = jwtTokenProvider.getEmailFromToken(refreshToken);

                    // 2. DB에서 사용자 정보 조회
                    Member member = memberRepository.findByEmailAndDeleteYn(email, "N")
                            .orElseThrow(() -> new RuntimeException("사용자 없음"));

                    // 3. Member → Authentication 수동 생성
                    Authentication authentication = new UsernamePasswordAuthenticationToken(
                            member, null, member.getAuthorities()
                    );

                    // 4. SecurityContext에 설정
                    SecurityContextHolder.getContext().setAuthentication(authentication);

                    // 5. 새로운 accessToken 생성
                    JwtDto newTokens = jwtTokenProvider.generateToken(authentication);
                    httpResponse.setHeader("accessToken", newTokens.getAccessToken());
                    httpResponse.setHeader("refreshToken", newTokens.getRefreshToken());
                }
                else {
                    sendErrorResponse(httpResponse, "refreshToken이 만료되었거나 유효하지 않습니다. 다시 로그인하세요", HttpServletResponse.SC_UNAUTHORIZED);
                    return;

//                    httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//                    httpResponse.setContentType("application/json;charset=UTF-8");
//                    httpResponse.getWriter().write("{\"message\": \"refreshToken이 만료되었거나 유효하지 않습니다. 다시 로그인하세요.\"}");
//                    return;
                }
            }
        }
        chain.doFilter(request, response);
    }

    /**
     * Request Header에서 토큰 정보 추출
     */
    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    /**
     * JWT 검증을 건너뛸지 판단하는 메서드
     */
    private boolean shouldSkipFilter(HttpServletRequest request) {
        String requestURI = request.getRequestURI();

        for (String excludePath : EXCLUDE_PATHS) {
            if (excludePath.endsWith("/**")) {
                // 와일드카드 패턴 처리
                String basePath = excludePath.substring(0, excludePath.length() - 3);
                if (requestURI.startsWith(basePath)) {
                    return true;
                }
            } else if (requestURI.equals(excludePath)) {
                return true;
            }
        }
        return false;
    }


    /**
     * 에러 응답 전송
     */
    private void sendErrorResponse(HttpServletResponse response, String message, int status)
            throws IOException {
        response.setStatus(status);
        response.setContentType("application/json;charset=UTF-8");

        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("error", true);
        errorResponse.put("message", message);
        errorResponse.put("timestamp", System.currentTimeMillis());

        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }
}