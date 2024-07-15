package com.beyond.basic.repository;

import com.beyond.basic.domain.Member;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
// 해당 레포지토리를 mybatis 기술을 쓰겠다는 표현
@Mapper
public interface MemberMybatisRepository extends MemberRepository {

}
