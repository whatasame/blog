package com.github.whatasame.study.jackson.vo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Student {

    @JsonProperty("student_name")
    private final String name;

    private final double grade;

    /**
     * 기본 생성자가 없고 생성자가 하나인 경우 @JsonCreator를 사용하지 않아도 된다.
     */
    @JsonCreator
    public Student(
        final String name,
        final double grade
    ) {
        this.name = name;
        this.grade = grade;
    }

    public Student(
        final String name,
        final double grade,
        final String message
    ) {
        this(name, grade);
        System.out.println("학생이 생성되었습니다." + message);
    }

    public String getName() {
        return this.name;
    }

    public double getGrade() {
        return this.grade;
    }
}
