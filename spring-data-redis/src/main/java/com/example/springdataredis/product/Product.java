package com.example.springdataredis.product;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("product")
@RequiredArgsConstructor
@Getter
public class Product {

    private final Long id;
    private final String name;
}
