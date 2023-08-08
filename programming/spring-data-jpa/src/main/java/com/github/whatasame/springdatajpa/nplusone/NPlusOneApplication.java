package com.github.whatasame.springdatajpa.nplusone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NPlusOneApplication {

    public static void main(String[] args) {
        SpringApplication.run(
                NPlusOneApplication.class,
                "--spring.config.location=classpath:/nplusone/application.yml"
        );
    }
}
