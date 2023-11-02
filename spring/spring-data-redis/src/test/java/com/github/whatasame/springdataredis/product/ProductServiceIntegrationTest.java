package com.github.whatasame.springdataredis.product;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.github.whatasame.springdataredis.base.RedisBaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@DisplayName("통합 테스트: ProductService")
@SpringBootTest
class ProductServiceIntegrationTest extends RedisBaseTest {

    @Autowired
    ProductService productService;

    @Test
    @DisplayName("상품을 저장한다.")
    void saveProduct() throws Exception {
        /* given */
        final Product 치킨 = new Product(1L, "치킨");

        /* when */
        final Product saved = productService.saveProduct(치킨);

        /* then */
        assertAll(
            () -> assertThat(saved.getId()).isEqualTo(치킨.getId()),
            () -> assertThat(saved.getName()).isEqualTo(치킨.getName())
        );
    }
}
