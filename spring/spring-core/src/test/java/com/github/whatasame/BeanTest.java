package com.github.whatasame;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.github.whatasame.circus.Animal;
import com.github.whatasame.circus.Audience;
import com.github.whatasame.circus.Cat;
import com.github.whatasame.circus.Circus;
import com.github.whatasame.circus.Dog;
import com.github.whatasame.circus.Man;
import com.github.whatasame.circus.Owner;
import com.github.whatasame.circus.Ticket;
import com.github.whatasame.circus.Woman;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
@DisplayName("학습 테스트: 빈 테스트")
class BeanTest {

    @Autowired
    ApplicationContext applicationContext;

    @Test
    @DisplayName("@Componet의 기본 이름은 클래스 이름에서 첫 문자를 소문자로 바꾼 문자열이다.")
    void componentDefaultNamingRule() throws Exception {
        /* given */


        /* when */


        /* then */
        assertThat(applicationContext.getBean("ticket"))
            .isExactlyInstanceOf(Ticket.class);
    }

    @Test
    @DisplayName("@Component.value를 이용하여 Bean의 이름을 변경할 수 있다.")
    void changeBeanNameWithComponentAnnotation() throws Exception {
        /* given */


        /* when */

        /* then */
        assertThat(applicationContext.getBean("오우너"))
            .isExactlyInstanceOf(Owner.class);
    }

    @Test
    @DisplayName("@Bean의 메서드 이름을 이용하여 Bean의 이름을 변경할 수 있다.")
    void changeBeanNameWithMethodName() throws Exception {
        /* given */


        /* when */


        /* then */
        assertThat(applicationContext.getBean("ManManMan"))
            .isInstanceOf(Audience.class)
            .isExactlyInstanceOf(Man.class);
    }

    @Test
    @DisplayName("@Bean.value를 이용하여 Bean의 이름을 변경할 수 있다.")
    void changeBeanNameWithBeanAnnotation() throws Exception {
        /* given */


        /* when */


        /* then */
        assertThat(applicationContext.getBean("Woman_woman_WOMAN"))
            .isInstanceOf(Audience.class)
            .isExactlyInstanceOf(Woman.class);
    }

    @Test
    @DisplayName("동일한 유형의 빈이 두 개 이상인 경우 Bean을 가져올 때 예외를 던진다.")
    void duplicatedBeanType() throws Exception {
        /* given */


        /* when */


        /* then */
        assertThatCode(() -> applicationContext.getBean(Animal.class))
            .isExactlyInstanceOf(NoUniqueBeanDefinitionException.class);
    }

    @Test
    @DisplayName("동일한 유형의 빈이 두 개 이상인 경우 Qualifier를 이용하여 구분할 수 있다.")
    void distinctWithQualifier() throws Exception {
        /* given */


        /* when */
        final Circus circus = applicationContext.getBean(Circus.class);

        /* then */
        assertAll(
            () -> assertThat(circus.getMain()).isExactlyInstanceOf(Cat.class),
            () -> assertThat(circus.getSub()).isExactlyInstanceOf(Dog.class)
        );
    }
}
