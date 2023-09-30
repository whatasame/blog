package com.github.whatasame.springweb.webenvironment;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DisplayName("학습 테스트: WebEnvironment - RANDOM_PORT")
class WebEnvironmentRandomPortTest {

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    Environment environment;

    @Test
    @DisplayName("WebEnvironment 값이 RANDOM_PORT일 때 "
        + "ServletWebServerApplicationContext를 생성하고 "
        + "임의의 포트 번호를 갖는 내장 톰캣 서버를 띄운다.")
    void randomPortTest() {
        /* given */


        /* when */


        /* then */
        assertThat(applicationContext)
            .isInstanceOf(ServletWebServerApplicationContext.class); // Embedded Servlet web server
        assertThat(environment.getProperty("local.server.port"))
            .isNotEqualTo("8080"); // Random port number
    }
}
