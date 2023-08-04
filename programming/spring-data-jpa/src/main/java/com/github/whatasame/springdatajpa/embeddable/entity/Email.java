package com.github.whatasame.springdatajpa.embeddable.entity;

import jakarta.persistence.Embeddable;

@Embeddable
public class Email {

    private String value;

    protected Email() {
    }

    public Email(final String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
