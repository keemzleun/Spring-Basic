package com.beyond.basic.domain;

import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
// 기본적으로 Entity는 상속관계가 불가능하여, 해당 어노테이션을 붙여야 상속관계 성립 가능
@MappedSuperclass
public abstract class BaseEntity {
    // 최초의 시간 생성
    @CreationTimestamp  // DB에는 current_timestamp가 생성되지 않음 = spring에서 시간을 찍어서 db에 넣어줌
    private LocalDateTime createdTime;  // camelCase로 작성시 db에는 _(언더바)로 생성

    // 시간 업데이트
    @UpdateTimestamp
    private LocalDateTime updateTime;
}
