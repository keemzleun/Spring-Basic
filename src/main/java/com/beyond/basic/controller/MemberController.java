package com.beyond.basic.controller;

import com.beyond.basic.domain.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MemberController {
    // 회원 목록 조회
    @GetMapping("/member/list")
    public String memberList(){


        return "/member/member-list";
    }

    // 회원 상세 조회 : memberDetail
    // url : member/1
    // 화면명 : member-detail
    @GetMapping("/member/{id}")
    // int 또는 Long으로 받을 경우, 스프링에서 형변환(String -> Long)
    public String memberDetail(@PathVariable Long id){

        return "/member/member-detail";
    }

    // 회원 가입 화면을 먼저 주고
    @GetMapping("/member/create")
    public String memberCreate(){
        return "member/member-create";
    }
    // 회원 가입 데이터를 받는다
    @PostMapping("/member/create")
    public Member memberCreatePost(Member member)
    {
        return null;
    }
}
