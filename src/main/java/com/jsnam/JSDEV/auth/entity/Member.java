package com.jsnam.JSDEV.auth.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "userId")
public class Member implements UserDetails {

    @Id
    @Column(name = "user_id", nullable = false, length = 50)
    private String userId;

    @Column(name = "user_name", length = 50)
    private String userName;

    @Column(name = "user_email", length = 50)
    private String userEmail;

    @Column(name = "user_phone", length = 50)
    private String userPhone;

    @Column(name = "user_image", length = 50)
    private String userImage;

    @Column(name = "user_password", nullable = false, length = 100)
    private String userPassword;

    @Column(name = "login_type", nullable = false, length = 50)
    private String loginType;

    @Column(name = "provider_id", length = 50)
    private String providerId;

    @CreationTimestamp
    @Column(name = "created")
    private LocalDateTime created;

    @UpdateTimestamp
    @Column(name = "updated")
    private LocalDateTime updated;

    @Column(name = "deleteYn", nullable = false, length = 1)
    private String deleteYn;


    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return this.userPassword;
    }

    @Override
    public String getUsername() {
        return this.userId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
