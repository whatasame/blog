package com.github.whatasame.springdatajpa.instagram;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "post_auto_incrs")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostAutoIncr {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    public PostAutoIncr(final Long userId) {
        this.userId = userId;
    }
}
