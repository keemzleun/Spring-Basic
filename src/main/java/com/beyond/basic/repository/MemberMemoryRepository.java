package com.beyond.basic.repository;

import com.beyond.basic.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

// 해당 클래스가 Repository 계층임을 표현함과 동시에 싱글톤 객체로 생성
@Repository
public class MemberMemoryRepository implements MemberRepository{

    private final List<Member> memberList;

    MemberMemoryRepository(){
        this.memberList = new ArrayList<>();
    }

    @Override
    public Member save(Member member) {
        memberList.add(member);
        return member;
    }

    @Override
    public List<Member> findAll() {
        return memberList;
    }

    @Override
    public Member findById(Long id) {
        return null;
    }
}
