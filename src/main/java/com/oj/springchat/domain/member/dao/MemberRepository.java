package com.oj.springchat.domain.member.dao;

import com.oj.springchat.domain.member.domain.Member;
import com.oj.springchat.domain.model.Email;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MemberRepository extends JpaRepository<Member,Long> {
    Member findByEmail(Email email);

    int deleteByEmail(Email email);

    boolean existsByEmail(Email email);
}
