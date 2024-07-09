package com.beyond.basic.domain;

import lombok.*;

// lombok 라이브러리를 통해 getter, setter 어노테이션 사용
//@Setter
//@Getter
@Data // getter, setter, toString 등을 포함
//@NoArgsConstructor : 기본 생성자를 만드는 어노테이션
//@AllArgsConstructor : 모든 매개변수를 상요한 생성자를 만드는 어노테이션
public class Hello {
    private String name;
    private String email;
    private String password;

//    @Override
//    public String toString(){
//        return "이름: " + this.name + " | email: " + this.email;
//    }
}
