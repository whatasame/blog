package com.github.whatasame.springdatajpa.attributeconverter.entity;

import java.util.Objects;
import org.springframework.util.StringUtils;

public class MemberName {

    private final String value;

    public MemberName(final String value) {
        if (!StringUtils.hasText(value)) {
            throw new IllegalArgumentException("회원 이름은 반드시 입력해야합니다.");
        }

        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MemberName that = (MemberName) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
