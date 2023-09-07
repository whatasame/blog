package com.github.whatasame.springdatajpamysql;

import com.github.whatasame.springdatajpamysql.mouse.MouseRepository;
import com.github.whatasame.springdatajpamysql.mouse.Mouse;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class BatchQueryTest {

    @Autowired
    MouseRepository mouseRepository;

    @BeforeEach
    void setUp() {
        for (int i = 0; i < 1000; i++) {
            Mouse mouse = new Mouse(i + "번 쥐");
            mouseRepository.save(mouse);
        }
    }

    @Test
    @DisplayName("각각의 쿼리로 마우스를 저장한다.")
    void individualQuery() throws Exception {
        /* given */

        /* when */
        long startTime = System.currentTimeMillis();
        for (long i = 0; i < 1000; i++) {
            mouseRepository.updateNameById(i, "시골쥐");
        }
        long endTime = System.currentTimeMillis();

        /* then */
        System.out.println("실행 시간: " + (endTime - startTime) + "ms");
    }

    @Test
    @DisplayName("Batch 쿼리로 마우스를 한 번에 저장한다.")
    void batchQuery() throws Exception {
        /* given */
        final List<Mouse> mice = mouseRepository.findAll();

        /* when */
        long startTime = System.currentTimeMillis();
        for (Mouse mouse : mice) {
            mouse.updateName("서울쥐");
        }
        mouseRepository.flush();
        long endTime = System.currentTimeMillis();

        /* then */
        System.out.println("실행 시간: " + (endTime - startTime) + "ms");
    }
}
