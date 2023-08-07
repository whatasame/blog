package com.github.whatsame.springweb.study.webenvironment;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
class WebEnvironmentMockTest {

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    Environment environment;

    @Test
    @DisplayName("WebEnvironment 값이 MOCK일 때 "
        + "ServletWebServerApplicationContext가 아닌 "
        + "Mock servlet GenericWebApplicationContext를 생성한다. (Mock servlet이므로 port 없음)")
    void mockTest() {
        /* given */


        /* when */


        /* then */
        assertThat(applicationContext)
            .isNotInstanceOf(ServletWebServerApplicationContext.class); // Mock Servlet web server
        assertThat(environment.getProperty("local.server.port"))
            .isNull(); // Mock servlet
    }
}
