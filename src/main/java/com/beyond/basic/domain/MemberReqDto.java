package com.beyond.basic.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberReqDto {
    private String name;
    private String email;
    private String password;

    // dto에서 entity로 변환
    // 추후에는 빌더 패턴으로 변환
    public Member toEntity(){
        // 매개 변수 필요업음 reqDto를 대상으로 만드는 거라
        Member member = new Member(this.name, this.email, this.password);
        return member;
    }
}
