package com.beyond.basic.repository;

import com.beyond.basic.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
// MemberRepository가 되기 위해서는 JpaRepository를 상속해야 하고, 상속시에 엔티티명과 엔티티의 pk 타입을 작성해야 함
// MemberRepository는 JpaRepository를 상속함으로서 JpaRepository의 주요 기능을 상속
// JpaRepository가 인터페이스임에도 해당 기능을 상속해서 사용할 수 잇는 이유는 hibernate구현체가 미리 구현돼 있기 때문
// 런타임 시점에 사용자가 인터페이스에 정의한 메서드를 리플렉션 기술을 통해 메서드를 구현
public interface MyMemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);
    List<Member> findByName(String name);
}
