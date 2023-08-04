package com.github.whatasame.springdatajpa.embeddable.entity;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
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

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "main_email"))
    private Email mainEmail;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "back_up_email"))
    private Email backUpEmail;

    protected Member() {
    }

    public Member(
        final MemberName name,
        final Email mainEmail,
        final Email backUpEmail
    ) {
        this.name = name;
        this.mainEmail = mainEmail;
        this.backUpEmail = backUpEmail;
    }

    public Long getId() {
        return this.id;
    }

    public MemberName getName() {
        return this.name;
    }

    public Email getMainEmail() {
        return this.mainEmail;
    }

    public Email getBackUpEmail() {
        return this.backUpEmail;
    }
}
