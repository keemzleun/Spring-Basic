package com.beyond.basic.domain;

import lombok.Data;

import java.util.List;

@Data
public class Student {
    private String name;
    private String email;
    private List<Student.Grade> grades;

    @Data
    private static class Grade {
        private String className;
        private String point;
    }
}
