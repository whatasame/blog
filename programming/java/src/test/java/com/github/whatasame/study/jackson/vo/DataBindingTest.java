package com.github.whatasame.study.jackson.vo;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Jackson 직렬화/역직렬화 테스트")
class DataBindingTest {

    ObjectMapper om;

    @BeforeEach
    void setUp() {
        om = new ObjectMapper();
        om.registerModule(new ParameterNamesModule(JsonCreator.Mode.PROPERTIES));
    }

    @Test
    @DisplayName("학생 정보를 직렬화한다.")
    void serializeStudent() throws JsonProcessingException {
        /* given */
        final Student student = new Student("홍길동", 4.1);

        /* when */
        final String json = om.writeValueAsString(student);

        /* then */
        assertThat(json).isEqualTo("{\"student_name\":\"홍길동\",\"grade\":4.1}");
    }

    @Test
    @DisplayName("학생 정보를 역직렬화한다.")
    void deserializeStudent() throws JsonProcessingException {
        /* given */
        final String json = """
            {
              "student_name" : "홍길동",
              "grade" : 4.1
            }
            """;

        /* when */
        final Student student = om.readValue(json, Student.class);

        /* then */
        assertThat(student).isNotNull();
    }

    @Test
    @DisplayName("주소 정보를 직렬화한다.")
    void serializeAddress() throws JsonProcessingException {
        /* given */
        final Address address = new Address("서울 송파구 올림픽로 295 7층");

        /* when */
        final String json = om.writeValueAsString(address);

        /* then */
        assertThat(json).isEqualTo("{\"address\":\"서울 송파구 올림픽로 295 7층\"}");
    }

    @Test
    @DisplayName("주소 정보를 역직렬화한다.")
    void deserializeAddress() throws JsonProcessingException {
        /* given */
        final String json = """
            {
              "address" : "서울 송파구 올림픽로 295 7층"
            }
            """;

        /* when */
        final Address address = om.readValue(json, Address.class);

        /* then */
        assertThat(address).isNotNull();
    }
}
