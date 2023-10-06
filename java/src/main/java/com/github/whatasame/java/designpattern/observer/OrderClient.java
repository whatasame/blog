package com.github.whatasame.java.designpattern.observer;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderClient {

    private final OrderSubject subject;

    public void order(final String itemName) {
        System.out.println("[OrderClient] 주문이 완료되었습니다. 주문 상품: " + itemName);

        subject.notify(itemName);
    }
}
