package com.github.whatasame.springdatajpa.count;

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

    @Column(name = "size")
    private long size;

    public Count() {
        this.size = 0L;
    }

    public void increaseCountOne() {
        this.size = size + 1;
    }
}
