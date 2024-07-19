package com.beyond.basic.controller;

import com.beyond.basic.domain.*;
import com.beyond.basic.repository.MemberRepository;
import com.beyond.basic.repository.MyMemberRepository;
import com.beyond.basic.service.MemberService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
// RestController의 경우 모든 메서드 상단에 @ResponseBody가 붙는 효과 발생
@RequestMapping("/rest")
@Api(tags = "회원관리서비스")
public class MemberRestController {

    private final MemberService memberService;
    private final MyMemberRepository myMemberRepository;
    @Autowired
    public MemberRestController(MemberService memberService, MyMemberRepository myMemberRepository) {
        this.memberService = memberService;
        this.myMemberRepository = myMemberRepository;
    }

    // HTTP 응답 메세지 테스트
    @GetMapping("/member/text")
    public String memberText(){
        return "ok";
    }


    // 회원 목록 조회
    @GetMapping("/member/list")
    public CommonResDto memberList() {

        List<MemberResDto> memberList = memberService.memberList();
//        return memberList;
        return new CommonResDto(HttpStatus.CREATED, "ok", memberList);
    }

    @GetMapping("/member/detail/{id}")
    public ResponseEntity<Object> memberDetail(@PathVariable Long id){

        try {
            MemberDetailResDto memberDetailResDto = memberService.memberDetail(id);
            CommonResDto commonResDto = new CommonResDto(HttpStatus.OK, "member is found", memberDetailResDto);
            return new ResponseEntity<>(commonResDto, HttpStatus.OK);
        } catch (EntityNotFoundException e){
            return new ResponseEntity<>(new CommonErrorDto(HttpStatus.NOT_FOUND, e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/member/create")
    public ResponseEntity<Object> memberCreatePost(@RequestBody MemberReqDto dto) {
        try {
            memberService.memberCreate(dto);
            CommonResDto commonResDto = new CommonResDto(HttpStatus.CREATED, "member is successfully created", null);
            return new ResponseEntity<>(commonResDto, HttpStatus.CREATED);
        } catch(IllegalArgumentException e){
            e.printStackTrace();
            CommonErrorDto commonErrorDto = new CommonErrorDto(HttpStatus.BAD_REQUEST, e.getMessage());
            return new ResponseEntity<>(commonErrorDto, HttpStatus.BAD_REQUEST);
        }
    }

    // 수정은 2가지 요청방식 : PUT, PATCH 요청
    // PUT 요청 : 덮어쓰기
    // PATCH 요청 : 부분 수정
    @PatchMapping("/member/pw/update")
    public String memberList(@RequestBody MemberUpdateDto dto){
        memberService.pwUpdate(dto);
        return "ok";
    }

    @DeleteMapping("/member/delete/{id}")
    public String memberDelete(@PathVariable Long id){
        memberService.delete(id);
        return "ok";
    }

    // lazy(지연 로딩), eager(즉시로딩) 테스트
    @GetMapping("/member/post/all")
    public void memberPostAll(){
        System.out.println(myMemberRepository.findAll());
    }
}
