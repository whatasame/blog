package com.github.whatsame.springweb.study.handlermethodargumentresolver.config;

import com.github.whatsame.springweb.study.handlermethodargumentresolver.resolver.HeaderUserArgumentResolver;
import java.util.List;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addArgumentResolvers(final List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new HeaderUserArgumentResolver());
    }
}
