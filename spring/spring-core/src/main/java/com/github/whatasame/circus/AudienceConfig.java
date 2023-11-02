package com.github.whatasame.circus;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AudienceConfig {

    @Bean
    public Audience ManManMan() {
        return new Man();
    }

    @Bean("Woman_woman_WOMAN")
    public Audience women() {
        return new Woman();
    }
}
