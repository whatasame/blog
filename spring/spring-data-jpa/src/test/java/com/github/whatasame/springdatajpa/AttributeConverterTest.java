package com.github.whatasame.springdatajpa;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.whatasame.springdatajpa.student.Student;
import com.github.whatasame.springdatajpa.student.StudentName;
import com.github.whatasame.springdatajpa.student.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@DisplayName("학습 테스트: AttributeConverter ")
class AttributeConverterTest {

    @Autowired
    StudentRepository studentRepository;

    @BeforeEach
    void setUp() {
        final Student 커비 = Student.builder()
            .name("커비")
            .age(7)
            .build();

        studentRepository.save(커비);
    }

    @Test
    @DisplayName("AttributeConverter로 이름을 매핑한다.")
    void convertStudentName() throws Exception {
        /* given */
        final StudentName name = new StudentName("커비");

        /* when */
        final Student 커비 = studentRepository.findByName(name)
            .orElseThrow(() -> new IllegalStateException("테스트 데이터가 존재하지 않습니다."));

        /* then */
        assertThat(커비.getName()).isEqualTo("커비");
    }
}
