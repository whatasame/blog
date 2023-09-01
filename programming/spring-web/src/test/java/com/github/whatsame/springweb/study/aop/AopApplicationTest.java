package com.github.whatsame.springweb.study.aop;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class AopApplicationTest {

    @Autowired
    MockMvc mockMvc;

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
}
