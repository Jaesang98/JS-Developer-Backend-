package com.jsnam.JSDEV.auth.repository;

import com.jsnam.JSDEV.auth.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String> {
    Optional<Member> findByUserIdAndDeleteYn(String userId, String deleteYn);
}
