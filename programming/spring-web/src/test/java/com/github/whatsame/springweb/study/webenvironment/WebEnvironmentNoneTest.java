package com.github.whatsame.springweb.study.webenvironment;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.context.WebServerApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

@SpringBootTest(webEnvironment = WebEnvironment.NONE)
class WebEnvironmentNoneTest {

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    Environment environment;

    @Test
    @DisplayName("WebEnvironment 값이 NONE일 때 "
        + "WebServerApplicationContext가 아닌 ApplicationContext를 생성하고 "
        + "SpringApplication의 WebApplicationType을 NONE으로 지정한다. (톰캣 서버를 띄우지 않으므로 port 없음.)")
    void noneTest() {
        /* given */


        /* when */


        /* then */
        assertThat(applicationContext)
            .isNotInstanceOf(WebServerApplicationContext.class); // No web server
        assertThat(environment.getProperty("local.server.port"))
            .isNull(); // WebApplicationType.NONE
    }
}
