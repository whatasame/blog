package com.github.whatasame.java;

import com.github.whatasame.java.designpattern.observer.OrderClient;
import com.github.whatasame.java.designpattern.observer.OrderSubject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Observer 패턴 테스트")
class ObserverTest {

    @Test
    @DisplayName("주문을 시작하면 Subject는 Observer들에게 알림을 보내고 Observer들은 이를 처리한다.")
    void observer() {
        /* given */
        final OrderSubject subject = new OrderSubject();
        subject.register(
            (itemName) -> System.out.println("[결제 시스템] " + itemName + "의 결제 완료"));
        subject.register(
            (itemName) -> System.out.println("[재고 시스템] " + itemName + "의 재고 감소 "));
        subject.register(
            (itemName) -> System.out.println("[배송 시스템] " + itemName + "의 배송 시작 "));

        final OrderClient orderClient = new OrderClient(subject);

        /* when */
        orderClient.order("아이폰15 프로");

        /* then */
        // OrderClient -> 결제 시스템 -> 재고 시스템 -> 배송 시스템 순으로 처리하는지 확인
    }
}
