package com.github.whatasame.springdatajpa.student;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.util.StringUtils;

@Getter
@EqualsAndHashCode
public class StudentName {

    private final String name;

    public StudentName(final String name) {
        validateIsEmpty(name);

        this.name = name;
    }

    private void validateIsEmpty(final String name) {
        if (!StringUtils.hasText(name)) {
            throw new IllegalArgumentException("학생 이름이 존재하지 않습니다.");
        }
    }
}
