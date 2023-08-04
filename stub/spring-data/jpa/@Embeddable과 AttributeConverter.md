# @Embeddable과 AttributeConverter

## @Embeddable

* 장점: 여러 필드 값을 이용하여 하나의 객체로 매핑할 수 있다.
* 단점: 기본 생성자가 필요하여 불변 객체로 표현하기 힘들다.

## AttributeConverter

* 장점: 기본 생성자가 필요하지 않아 불변 객체로 만들기 용이하다.
* 단점: 2개 이상의 필드를 갖는 객체를 정의할 수 없다.

## 결론

원시값을 포장하기 위한 객체는 AttributeConverter를 적극적으로 활용해보자.

## 참고자료

[AttributeConverter vs Embeddable in JPA](https://www.wimdeblauwe.com/blog/2021/03/01/attributeconverter-vs-embeddable-in-jpa/)

