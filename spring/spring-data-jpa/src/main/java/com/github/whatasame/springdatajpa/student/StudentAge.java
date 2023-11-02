package com.github.whatasame.springdatajpa.student;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StudentAge {

    private int age;

    public StudentAge(final int age) {
        validateIsNotLessThanZero(age);

        this.age = age;
    }

    private void validateIsNotLessThanZero(final int age) {
        if (age < 0) {
            throw new IllegalArgumentException("나이는 0살 미만일 수 없습니다.");
        }
    }
}
