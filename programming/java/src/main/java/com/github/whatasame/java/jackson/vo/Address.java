package com.github.whatasame.java.jackson.vo;

import com.fasterxml.jackson.annotation.JsonCreator;

public class Address {

    private final String address;

    /**
     * 인스턴스 필드가 하나인 경우 @JsonCreator를 반드시 달아야한다.
     */
    @JsonCreator
    public Address(final String address) {
        this.address = address;
    }

    public String getAddress() {
        return this.address;
    }
}
