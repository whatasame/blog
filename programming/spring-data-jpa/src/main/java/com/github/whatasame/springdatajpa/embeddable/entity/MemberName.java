package com.github.whatasame.springdatajpa.embeddable.entity;

import jakarta.persistence.Embeddable;

@Embeddable
public class MemberName {

    private String firstName;
    private String lastName;

    protected MemberName() {
    }

    public MemberName(final String firstName, final String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }
}
