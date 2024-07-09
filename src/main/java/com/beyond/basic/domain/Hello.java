package com.beyond.basic.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

// lombok 라이브러리를 통해 getter, setter 어노테이션 사용
//@Setter
//@Getter
@Data // getter, setter, toString 등을 포함
public class Hello {
    private String name;
    private String email;
    private String password;

//    @Override
//    public String toString(){
//        return "이름: " + this.name + " | email: " + this.email;
//    }
}
