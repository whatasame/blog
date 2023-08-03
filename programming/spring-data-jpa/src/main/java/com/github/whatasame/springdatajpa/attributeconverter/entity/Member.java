package com.github.whatasame.springdatajpa.attributeconverter.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "members")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private MemberName name;
    private MemberAge age;

    protected Member() {
    }

    public Member(
        final MemberName name,
        final MemberAge age
    ) {
        this.name = name;
        this.age = age;
    }

    public Long getId() {
        return this.id;
    }

    public MemberName getName() {
        return this.name;
    }

    public MemberAge getAge() {
        return this.age;
    }
}
