package com.beyond.basic.domain;

import lombok.*;

// lombok 라이브러리를 통해 getter, setter 어노테이션 사용
//@Setter
//@Getter
@Data // getter, setter, toString 등을 포함
@NoArgsConstructor // : 기본 생성자를 만드는 어노테이션
@AllArgsConstructor // : 모든 매개변수를 사용한 생성자를 만드는 어노테이션
public class Hello {
    private String name;
    private String email;
    private String password;

//    @Override
//    public String toString(){
//        return "이름: " + this.name + " | email: " + this.email;
//    }

    // builder 패턴 직접 구현
    // builder 적용 대상 생성자가 필요
    public Hello(HelloBuilder helloBuilder){
        this.name = helloBuilder.name;
        this.email = helloBuilder.email;
        this.password = helloBuilder.password;

    }
    public static HelloBuilder builder() {
        return new HelloBuilder();
    }

    public static class HelloBuilder{
        private String name;
        private String email;
        private String password;

        public HelloBuilder name(String name){
            this.name = name;
            return this;    // HelloBuilder 객체 반환
        }
        public HelloBuilder email(String email){
            this.email = email;
            return this;
        }
        public HelloBuilder password(String password){
            this.password = password;
            return this;
        }
        public Hello build(){
            return new Hello(this);
        }
    }
}
