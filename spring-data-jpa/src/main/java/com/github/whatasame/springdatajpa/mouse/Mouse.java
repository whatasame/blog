package com.github.whatasame.springdatajpa.mouse;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "mice")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Mouse {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    public Mouse(final String name) {
        this.name = name;
    }

    public void updateName(final String name) {
        this.name = name;
    }
}
