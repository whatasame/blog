package com.github.whatasame.springweb.aop;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("학습 테스트: AOP")
class AopTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ApplicationContext applicationContext;

    @Test
    @DisplayName("GET 요청을 보내 로그가 정상적으로 찍히는지 확인한다.")
    void getCoke() throws Exception {
        /* given */
        final String url = "";

        /* when & then */
        mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .param("name", "펩시 제로 슈거 라임향")
                .param("volume", "210")
                .content("{\"greeting\": \"안녕하세요\"}")
            )
            .andExpect(status().isOk());
    }

    @Test
    @DisplayName("AOP 타겟 컴포넌트는 Proxy 객체로 생성된다.")
    void proxyComponent() throws Exception {
        /* given */

        /* when */
        final CokeController cokeController = applicationContext.getBean(CokeController.class);

        /* then */
        assertAll(
            () -> assertThat(cokeController).isInstanceOf(CokeController.class),
            () -> assertThat(cokeController).isNotExactlyInstanceOf(CokeController.class)
        );
    }

    @Test
    @DisplayName("일반 컴포넌트는 Proxy 객체로 생성되지 않는다.")
    void normalComponent() throws Exception {
        /* given */

        /* when */
        final SimpleComponent simpleComponent = applicationContext.getBean(SimpleComponent.class);

        /* then */
        assertAll(
            () -> assertThat(simpleComponent).isInstanceOf(SimpleComponent.class),
            () -> assertThat(simpleComponent).isExactlyInstanceOf(SimpleComponent.class)
        );
    }
}
