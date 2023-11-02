package com.github.whatasame.springdatajpa.instagram;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "posts")
@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class Post {

    @Id
    @GenericGenerator(
        type = PostIdGenerator.class,
        name = "post_id_generator"
    )
    @GeneratedValue(generator = "post_id_generator")
    @Column(name = "post_id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    public Post(final Long userId) {
        this.userId = userId;
    }
}
