package com.beyond.basic.service;

import com.beyond.basic.controller.MemberController;
import com.beyond.basic.domain.Member;
import com.beyond.basic.domain.MemberReqDto;
import com.beyond.basic.domain.MemberResDto;
import com.beyond.basic.repository.MemberJdbcRepository;
import com.beyond.basic.repository.MemberMemoryRepository;
import com.beyond.basic.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

// input값의 검증 및 실질적인 비즈니스 로직은 서비스 계층에서 수행
@Service    // 서비스 계층임을 표현함과 동시에 싱글톤 객체로 생성
public class MemberService {

    // 메서드마다 memberRepsotiry가 필요하니까(중복) 최상단에 선언
    // final : 최초에 memberService 객체가 선언될 때에만 memberRepository 초기화
    private final MemberRepository memberRepository;

    // 생성자가 호출될 때 memberRepository를 호출
    @Autowired  // 싱글톤 객체를 주입(DI)받는다는 것을 의미
    public MemberService(MemberJdbcRepository memoryRepository){
        this.memberRepository = memoryRepository;
    }

//    // 순환 참조 오류 발생 !!)  MemberController와 MemberService 서로 참조하고 있음
//    @Autowired
//    private MemberController memberController;

    public void memberCreate(MemberReqDto dto){
        if (dto.getPassword().length() < 8){
            throw new IllegalArgumentException("비밀번호가 너무 짧습니다.");
        }
        Member member = new Member();
        member.setName(dto.getName());
        member.setEmail(dto.getEmail());
        member.setPassword(dto.getPassword());
        memberRepository.save(member);
    }

    public Member memberDetail(Long id){
        return memberRepository.findById(id);
    }

    public List<MemberResDto> memberList(){
        List<MemberResDto> memberResDtos = new ArrayList<>();
        List<Member> memberList = memberRepository.findAll();

        for(Member m : memberList){
            MemberResDto dto = new MemberResDto();
            dto.setName(m.getName());
            dto.setEmail(m.getEmail());
            memberResDtos.add(dto);
        }
        return memberResDtos;
    }
}
