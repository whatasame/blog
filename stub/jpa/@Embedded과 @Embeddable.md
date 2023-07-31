# @Embedded과 @Embeddable

## 언제 사용하나?

별도의 클래스를 정의하지 않고 데이터베이스의 값을 추상화하기 위하여 사용한다.

나의 경우 원시 값을 포장할 때 데이터베이스에 있는 값을 포장하는 클래스에 매핑하기 위해 사용했다.

## @Embedded

직역하면 내장된. 내맘대로 해석하면 데이터베이스에는 없지만 별도로 **내장된** 클래스 값이다.

## @Embeddable

직역하면 내장할 수 있는. 내맘대로 해석하면 데이터베이스의 값으로 **내장될 수 있는** 클래스이다.

## 예제

```java

@Entity
@Table(name = "product")
public class Product {

    /* ... */

    @Column(name = "name")
    @Embedded // 별도로 내장되어 정의된 클래스가 있다.
    private ProductName name;

    /* ... */
}

```

```java

@Embeddable // 다른 곳에서 내장될 수 있다.
public class ProductName {

    private String name;

    /* ... */
}

```

## @AnnotationAttribute와 @AttributeOverrides

* @AnnotationAttribute로 데이터베이스 컬럼명과 클래스 필드명을 매핑할 수 있다.
* @AttributeOverrides를 이용하여 여러 개의 @AnnotationAttribute를 묶는다.

## 참고자료

[Jpa @Embedded and @Embeddable](https://www.baeldung.com/jpa-embedded-embeddable)
