package com.beyond.basic.service;

import com.beyond.basic.controller.MemberController;
import com.beyond.basic.domain.*;
import com.beyond.basic.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// input값의 검증 및 실질적인 비즈니스 로직은 서비스 계층에서 수행
@Service    // 서비스 계층임을 표현함과 동시에 싱글톤 객체로 생성
// Transactiopnal 어노테이션을 통해 모든 메서드에 트랜잭션을 적용하고, 만약에 예외가 있을 시 롤백처리
@Transactional
public class MemberService {

    // 다형성 설계
    // 메서드마다 memberRepsotiry가 필요하니까(중복) 최상단에 선언
    // final : 최초에 memberService 객체가 선언될 때에만 memberRepository 초기화
    private final MyMemberRepository memberRepository;

    // 생성자가 호출될 때 memberRepository를 호출
    @Autowired  // 싱글톤 객체를 주입(DI)받는다는 것을 의미
    public MemberService(MyMemberRepository myMemberRepository){
        this.memberRepository = myMemberRepository;
    }

//    // 비 다형성 설계
//    private final MyMemberRepository memberRepository;
//
//    // 생성자가 호출될 때 memberRepository를 호출
//    @Autowired  // 싱글톤 객체를 주입(DI)받는다는 것을 의미
//    public MemberService(MyMemberRepository memberRepository){
//        this.memberRepository = memoryRepository;
//    }


//    // 순환 참조 오류 발생 !!)  MemberController와 MemberService 서로 참조하고 있음
//    @Autowired
//    private MemberController memberController;

    public void memberCreate(MemberReqDto dto){
        if (dto.getPassword().length() < 8){
            throw new IllegalArgumentException("비밀번호가 너무 짧습니다.");
        }
        Member member = dto.toEntity();

        memberRepository.save(member);
    }

    public MemberDetailResDto memberDetail(Long id){
        Optional<Member> optMember = memberRepository.findById(id);

        // 클라이언트에게 적절한 예외메세지와 상태코드를 주는 것이 주요 목적
        // 또한 예외를 강제 발생시킴으로서 적절한 롤백처리 하는 것도 주요 목적
        Member member = optMember.orElseThrow(() -> new EntityNotFoundException("없는 회원입니다."));
        System.out.println("글쓴이의 쓸 글의 개수: " + member.getPosts());
        for (Post p : member.getPosts()){
            System.out.println("글의 제목: " + p.getTitle());
        }
        return member.detailFromEntity();
    }

    public List<MemberResDto> memberList(){
        List<MemberResDto> memberResDtos = new ArrayList<>();
        List<Member> memberList = memberRepository.findAll();

        for(Member m : memberList){
            // 방법 1)
//            MemberResDto dto = member.listFromEntity();
//            memberResDtos.add(dto);
            // 방법 2)
            memberResDtos.add(m.listFromEntity());
        }
        return memberResDtos;
    }

    public void pwUpdate(MemberUpdateDto dto){
        Member member = memberRepository.findById(dto.getId()).orElseThrow(()->new EntityNotFoundException("member is not found!"));
        member.updatePw(dto.getPassword());

        // 기본 객체를 조회후 수정한 다음에 save시에는 jpa가 update 실행
        memberRepository.save(member);
    }

    public void delete(Long id){

        Member member = memberRepository.findById(id).orElseThrow(()->new EntityNotFoundException("회원이 없습니다."));
        memberRepository.delete(member); // 완전 삭제

//        member.updateDelYn("Y");
//        memberRepository.save(member);
    }

}
