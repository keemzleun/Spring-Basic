package com.beyond.basic.domain;

import lombok.Data;

@Data
public class MemberReqDto {
    private String name;
    private String email;
    private String password;
}
