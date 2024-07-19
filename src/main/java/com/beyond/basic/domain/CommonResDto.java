package com.beyond.basic.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
public class CommonResDto {
    private int status_code;
    private String Status_message;
    private Object result;

    public CommonResDto(HttpStatus httpStatus, String message, Object result){
        this.status_code = httpStatus.value();
        this.Status_message = message;
        this.result = result;
    }
}