package com.beyond.basic.controller;

import com.beyond.basic.domain.CommonResDto;
import com.beyond.basic.domain.Member;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/response/entity")
public class ResponseEntityController {
    // case 1 ) @ResponseStatus 어노테이션
    @GetMapping("/annotation1")
    @ResponseStatus(HttpStatus.CREATED)
    public String annotation1(){
        return "ok";
    }

    @GetMapping("/annotation2")
    @ResponseStatus(HttpStatus.CREATED)
    public Member annotation2(){
        // (가정) 객체 생성 후 DB 저장 성공
        Member member = new Member("honhhh", "jkjh@naver.com", "12343526458909");
        return member;
    }



    // case 2 ) 메서드 체이닝 방식 : ResponseEntity의 클래스 메서드 사용
    @GetMapping("/chaining1")
    public ResponseEntity<Member> chaining1(){
        Member member = new Member("honhhh", "jkjh@naver.com", "12343526458909");
        return ResponseEntity.ok(member);
    }

    @GetMapping("/chaining2")
    public ResponseEntity<Member> chaining2(){
        Member member = new Member("honhhh", "jkjh@naver.com", "12343526458909");
        return ResponseEntity.status(HttpStatus.CREATED).body(member);
    }

    @GetMapping("/chaining3")
    public ResponseEntity<Member> chaining3(){
        return ResponseEntity.notFound().build();
    }



    // case 3 ) ResponseEntity 객체를 직접 커스텀하여 생성하는 방식
    @GetMapping("/custom1")
    public ResponseEntity<Member> custom1(){
        Member member = new Member("honhrhh", "jrrkjh@naver.com", "12343526458909");
        return new ResponseEntity<>(member, HttpStatus.CREATED);
    }

    @GetMapping("/custom2")
    public ResponseEntity<CommonResDto> custom2(){
        Member member = new Member("honhrhh", "jrrkjh@naver.com", "12343526458909");
        // HttpStatus.CREATED => 바디에 들어감
        CommonResDto commonResDto = new CommonResDto(HttpStatus.CREATED, "member is successfully created", member);
        // HttpStatus.CREATED => 헤더에 들어감
        return new ResponseEntity<>(commonResDto, HttpStatus.CREATED);
    }

}
