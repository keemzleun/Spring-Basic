package com.beyond.basic.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Getter
// entity 어노테이션을 통해 엔티티매니저에게 객체 관리를 위임
// 해당 클래스명으로 테이블 및 컬럼을 자동 생성하고, 각종 설정정보 위임
@Entity
@NoArgsConstructor  // 기본 생성자는 Jpa에서 필수
public class Member extends BaseEntity{

    @Id // pk 설정
    // identity: auto_increament 설정
    // auto: jpa 자동으로 적절한 전략을 선택하도록 맡기는 것
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    // Long은 bigint로 변환

    // String은 Varchar(255)로 기본으로 변환. name변수명이 name 컬럼명으로 변환
    private String name;

    // nullable=false : notnull 제약 조건
    @Column(nullable = false, length = 50, unique = true)
    private String email;

    // @Column(name = "pw")    // 컬럼명을 변경할 수 있으나, 일치시키는 것이 좋음
    private String password;

    // ManyToOne이 있어도 무조건 만들어야 하는 것은 아님. 필요에 의해 만드는 것
    @OneToMany(mappedBy = "member")
    private List<Post> posts;


    public Member(String name, String email, String password){
        this.name = name;
        this.email = email;
        this.password = password;
    }


    // password 상단에 @Setter를 통해 특정 변수만 setter 사용이 가능하나,
    // 일반적으로 의도를 명확하게 한 메서드를 별도로 만들어 사용하는 것을 권장
    public void updatePw(String password){
        this.password = password;
    }


    public MemberDetailResDto detailFromEntity(){
        LocalDateTime createdTime = this.getCreatedTime();
        String value = createdTime.getYear()+"년 "+createdTime.getMonthValue()+"월 "+createdTime.getDayOfMonth()+"일";

        return new MemberDetailResDto(this.id, this.name, this.email, this.password, value);
    }

    public MemberResDto listFromEntity(){
        return new MemberResDto(this.id, this.name, this.email);
    }
}
