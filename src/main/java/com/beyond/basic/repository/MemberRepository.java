package com.beyond.basic.repository;

import com.beyond.basic.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);

    List<Member> findAll();

    Optional<Member> findById(Long id);   // id를 매개변수로 받아 Member 객체를 반환


}
