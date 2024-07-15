package com.beyond.basic.repository;

import com.beyond.basic.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class MemberJpaRepository implements MemberRepository{
    // EntityManager는 Jpa의 핵심클래스(객체)
    // Entity의 생명주기를 관리, 데이터베이스와의 모든 인터페이싱을 책임
    // 즉, 엔티티를 대상으로 CRUD하는 기능을 제공
    @Autowired
    private EntityManager entityManager;

    @Override
    public Member save(Member member) {
        // persist: 전달된 엔티티(Member)가 EntityManager의 관리 상태가 되도록 만들어주고
        // 트랜잭션이 커밋될 때 데이터베이스에 저장. insert.
        entityManager.persist(member);
        return null;
    }

    @Override
    public List<Member> findAll() {
//        jpql : jpa의 raw쿼리 문법(객체지향쿼리문법)
//        Jpa에서는 jpql 문법 컴파일 에러가 나오지 않으나, springDataJpa에서는 컴파일 에러 발생
        List<Member> memberList = entityManager.createQuery("select m from Member m", Member.class).getResultList();
        return memberList;
    }
//    public Member findByEmail(String email){
//        Member member = entityManager.createQuery("select m from Member m where m.email = :eamil", Member.class).setParameter("email", email).getSingleResult();
//        return member;
//    }

    @Override
    public Optional<Member> findById(Long id) {
         // entityManager를 통해 find(리턴타입클래스지정 및 매개변수로 Pk 필요)
        Member member = entityManager.find(Member.class, id);
        return Optional.ofNullable(member);
    }
    // pk이외의 컬럼으로 조회할 땐 jpql문법으로 raw쿼리 비슷하게 직접 쿼리 작성
}
