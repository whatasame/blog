package com.github.whatsame.springweb.study.handlermethodargumentresolver;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@SpringBootTest
@AutoConfigureMockMvc
class HandleMethodArgumentResolverTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("Header에 회원 식별자 값을 넣고 요청하면 식별자를 응답한다.")
    void getMemberId() throws Exception {
        /* given */
        final String url = "/auth";
        final Long memberId = 5L;

        /* when */
        final ResultActions result = mockMvc
            .perform(
                get(url)
                    .header("memberId", memberId)
            );

        /* then */
        result.andExpect(content().string("회원 식별자: 5"));
    }
}
