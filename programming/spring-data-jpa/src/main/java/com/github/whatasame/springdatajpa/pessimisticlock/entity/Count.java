package com.github.whatasame.springdatajpa.pessimisticlock.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Getter
@Table(name = "counts")
public class Count {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "count")
    private long count;

    public Count() {
        this.count = 0L;
    }

    public void increaseCountOne() {
        this.count = count + 1;
    }
}
