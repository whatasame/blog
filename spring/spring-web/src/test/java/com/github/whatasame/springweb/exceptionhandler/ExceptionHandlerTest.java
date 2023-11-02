package com.github.whatasame.springweb.exceptionhandler;

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
@DisplayName("학습 테스트: ExceptionHandler")
class ExceptionHandlerTest {

    @Autowired
    MockMvc mockMvc;

    /**
     * Bean별 매핑 정보
     * <p>Controller: BothHandleException</p>
     * <p>ControllerAdvice: {1, 2}, 3 (parameter), BothHandleException</p>
     */

    @Test
    @DisplayName("/1/2/3이면 1로 1을 핸들링한다.")
    void handle1From123() throws Exception {
        /* given */
        final String url = "/1/2/3";

        /* when */
        final ResultActions result = mockMvc.perform(get("/1/2/3"));

        /* then */
        result.andExpect(
            content().string("ExceptionHandlerTestHandler#handle12 => HierarchyException1")
        );
    }

    @Test
    @DisplayName("/2/1/3이면 2으로 2를 핸들링한다.")
    void handle2From213() throws Exception {
        /* given */
        final String url = "/2/1/3";

        /* when */
        final ResultActions result = mockMvc.perform(get(url));

        /* then */
        result.andExpect(
            content().string("ExceptionHandlerTestHandler#handle12 => HierarchyException2")
        );
    }

    @Test
    @DisplayName("/3/1/2이면 3으로 3을 핸들링한다.")
    void handle3From312() throws Exception {
        /* given */
        final String url = "/3/1/2";

        /* when */
        final ResultActions result = mockMvc.perform(get(url));

        /* then */
        result.andExpect(
            content().string("ExceptionHandlerTestHandler#handle3 => HierarchyException3")
        );
    }

    @Test
    @DisplayName("/4/1/2이면 3으로 4를 핸들링한다.")
    void handle1From412() throws Exception {
        /* given */
        final String url = "/4/1/2";

        /* when */
        final ResultActions result = mockMvc.perform(get(url));

        /* then */
        result.andExpect(
            content().string("ExceptionHandlerTestHandler#handle3 => HierarchyException4")
        );
    }

    @Test
    @DisplayName("/unique/3/1/2이면 3으로 3을 핸들링한다.")
    void handle3FromUnique() throws Exception {
        /* given */
        final String url = "/unique/3/1/2";

        /* when */
        final ResultActions result = mockMvc.perform(get(url));

        /* then */
        result.andExpect(
            content().string("ExceptionHandlerTestHandler#handle3 => HierarchyException3")
        );
    }

    @Test
    @DisplayName("/bothHandleException이면 Controller에서 핸들링한다.")
    void handleBothHandleExceptionAtController() throws Exception {
        /* given */
        final String url = "/bothHandleException";

        /* when */
        final ResultActions result = mockMvc.perform(get(url));

        /* then */
        result.andExpect(
            content().string("Controller => BothHandleException")
        );
    }
}
