package com.github.whatasame.springdatajpa.attributeconverter.entity;

import java.util.Objects;

public class MemberAge {

    private final int value;

    public MemberAge(final int value) {
        if (value <= 0) {
            throw new IllegalArgumentException("나이는 1살 이상이어야 합니다.");
        }

        this.value = value;
    }

    public int getValue() {
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
        final MemberAge memberAge = (MemberAge) o;
        return value == memberAge.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
