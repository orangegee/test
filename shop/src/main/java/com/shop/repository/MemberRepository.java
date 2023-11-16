package com.shop.repository;

import com.shop.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    /**
     * 회원 가입 시 중복된 회원이 있는지 이메일로 검사할 수 있도록 쿼리 메소드 작성
     */
    Member findByEmail(String email);

}
