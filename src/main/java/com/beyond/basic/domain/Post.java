package com.beyond.basic.domain;

import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
public class Post extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    // 1:1의 경우 OneToOne을 설정하고, unique=true로 설정
    // ManyToOne, OneToOne의 경우 default 설정이 eager이므로, lazy로 변경
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    // jpa의 영속성(Persistence) 컨텍스트에 의해 Member가 관리
    private Member member;



    public PostResDto listFromEntity(){
        return new PostResDto(this.id, this.title);
    }
}
