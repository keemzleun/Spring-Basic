package com.beyond.basic.controller;

import com.beyond.basic.domain.Member;
import com.beyond.basic.domain.MemberReqDto;
import com.beyond.basic.domain.MemberResDto;
import com.beyond.basic.repository.MemberRepository;
import com.beyond.basic.service.MemberService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller     // 싱글톤임
public class MemberController {

//    // 의존성 주입(DI) 방법 1 ) 생성자 주입 방식(가장 많이 사용)
//    // 장점 : final을 통해 상수로 사용 가능 / 다형성 구현 가능 / 순환 참조 방지
    private final MemberService memberService;
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

//    // 의존성 주입 방법 2 ) 필드 주입 방식(Autoowired만 사용)
//    @Autowired
//    private MemberService memberService;

//    // 의존성 주입 방법 3 ) 어노테이션(@RequiredArgs)을 이용하는 방식
//    // class에 @RequiredArgsConstructor : @NonNull 어노테이션, final 키워드가 붙어 있는 필드를 대상으로 생성자 생성
//    private final MemberService memberService;

    // home 화면
    @GetMapping("/")
    public String home(){
        return "member/home";
    }


    // 회원 목록 조회
    @GetMapping("/member/list")
    public String memberList(Model model){
        List<MemberResDto> memberList = memberService.memberList();
        model.addAttribute("memberList", memberList);
        return "member/member-list";
    }

    // 회원 상세 조회 : memberDetail
    // url : member/1
    // 화면명 : member-detail
    @GetMapping("/member/{id}")
    // int 또는 Long으로 받을 경우, 스프링에서 형변환(String -> Long)
    public String memberDetail(@PathVariable Long id){
        return "member/member-detail";
    }


    // 회원 가입 화면을 먼저 주고
    @GetMapping("/member/create")
    public String memberCreate(){
        return "member/member-create";
    }
    // 회원 가입 데이터를 받는다
    @PostMapping("/member/create")
    public String memberCreatePost(MemberReqDto dto, Model model) {
        try {
            // MemberService memberService = new MemberService();
            memberService.memberCreate(dto);
            // 화면 리턴이 아닌 url 재호출
            return "redirect:/member/list";
        } catch(IllegalArgumentException e){
            model.addAttribute("message", e.getMessage());

            return "member/member-error";
        }
    }

}
