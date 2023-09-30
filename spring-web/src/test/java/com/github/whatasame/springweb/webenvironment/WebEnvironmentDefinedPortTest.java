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

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@DisplayName("학습 테스트: WebEnvironment - DEFINED_PORT")
class WebEnvironmentDefinedPortTest {

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    Environment environment;

    @Test
    @DisplayName("WebEnvironment 값이 DEFINED_PORT일 때 "
        + "ServletWebServerApplicationContext를 생성하고 "
        + "지정한 포트 번호를 갖는 내장 Servlet 톰캣 서버를 띄운다.")
    void definedPortTest() {
        /* given */


        /* when */


        /* then */
        assertThat(applicationContext)
            .isInstanceOf(ServletWebServerApplicationContext.class); // Embedded Servlet web server
        assertThat(environment.getProperty("local.server.port"))
            .isEqualTo("8080"); // Defined port number
    }
}
