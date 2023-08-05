# 디미터의 법칙(Law of Demeter)

## 객체와 자료구조

객체와 자료 구조는 명백히 다르다.

객체는 추상화로 자료를 숨긴 채 자료를 다루는 함수만 공개한다. 반대로 자료 구조는 자료를 그대로 공개하며 별다른 함수는 제공하지 않는다.

이는 객체는 조회 함수로 내부 구조를 공개하면 안된다는 것을 의미한다.

## 디미터의 법칙

디미터의 법칙에 의하면 클래스 `C`의 메서드 `f`는 다음과 같은 객체의 method만 호출해야한다.

1. 클래스 `C`
2. `f`에서 생성한 객체
3. `f` 인수로 넘어온 객체
4. `C` 인스턴스 변수에 저장된 객체

하지만 4가지 허용된 method를 사용하더라도 반환하는 객체의 method를 호출해선 안된다. 객체가 어떤 자료를 가지고 있는 지 다른 객체가 알기 때문이다.

## chaining != 디미터 법칙 위반

그러나 자료 구조의 경우 chaining을 하더라도 디미터 법칙을 위반하지 않는다. 자료 구조는 자료를 그대로 공개해야하기 때문이다.

## 출력을 위한 getter의 경우

다음과 같이 이름 정보를 갖는 학생 클래스가 있다고 하자.

```java
public class Student {

    private final Name name; // String 원시 값을 포장하는 VO

    /* constructor */
}
```

이때 API 응답 DTO를 만들기 위해 Student의 이름과 성적을 반환할 때 두 가지 방법이 있다.

```java

public class Student {

    /* 생략 */

    // case 1
    public Name getName() {
        return this.name;
    }

    // case 2
    public String getName() {
        return this.name.getValue();
    }
}
```

둘 중 디미터 법칙을 위반하는 것은 어떤 경우일까? 정답은 첫 번째이다. 왜냐하면 DTO에서 Student의 내부 구조를 알게 되기 때문이다.

```java
public class StudentResponse {

    private final String name;

    // case 1
    public static StudentResponse of(final Student student) {
        return new StudentResponse(student.getName().getValue()); // 디미터 법칙 위반
    }

    // case 2
    public static StudentResponse of(final Student student) {
        return new StudentResponse(student.getName()); // OK
    }
}
```

두 번째 방법을 사용하면 DTO는 Student 내부에 Name이라는 클래스가 있는 지 알 수 없다.

## 참고자료

[Clean code 6장 객체와 자료구조](https://www.yes24.com/Product/Goods/11681152)
