package com.jsnam.JSDEV.config;

import com.jsnam.JSDEV.auth.filter.JwtAuthenticationFilter;
import com.jsnam.JSDEV.auth.token.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // http.csrf(csrf -> csrf.disable()); 아래와 같이 쓸 수도 있음 / 같은 기능
        http
                // 폼 기반 요청(POST/PUT 등)에 대해 위조 요청을 막기 위해 사용
                .csrf(AbstractHttpConfigurer::disable)
                
                // 세션 비활성화
                .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // 요청 URL에 대한 권한 설정
                .authorizeHttpRequests(
                        authorize -> authorize
//                                .requestMatchers("/admin/**").hasAnyAuthority("ROLE_ADMIN")   // 관리자만 접근가능
//                                .requestMatchers("/member/join").authenticated() // 로그인한 사용자만 접근 가능 (권한은 필요 없음)
//                                .requestMatchers("/auth/login").permitAll()
                                .anyRequest().permitAll()
                )
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
