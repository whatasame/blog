# Generic type erasure

## 예제로 알아보자

다음과 같이 정수들을 관리하는 객체가 있다고 하자. 이 객체를 생성하기 위해선 Integer list가 필요하다.

```java

public class Numbers {

    private List<Integer> numbers;

    public Numbers(final List<Integer> numbers) {
        this.ints = numbers;
    }
}
```

Long도 정수이기 때문에 개발자는 편의를 위해 다음과 같이 Long list를 인자로 받는 생성자를 추가했다. 그러나 이 경우 컴파일 에러가 발생한다.

```java
public class Numbers {

    private List<Integer> ints;

    public Numbers(final List<Double> nums) { // clash
        this.ints = nums.stream()
            .map(Double::intValue)
            .toList();
    }

    public Numbers(final List<Integer> ints) {
        this.ints = ints;
    }
}
```

왜 이럴까? 이를 이해하기 위해선 Generic type의 생명주기를 알아야한다.

### Generic type은 compile time 이후 제거된다

Type parameter들은 compile에 의해 삭제된다. 따라서 방금 전 코드의 runtime에서 모습은 다음과 같아진다.

```java
public class Numbers {

    private List ints;

    public Numbers(final List nums) { // clash
        this.ints = nums.stream()
            .map(Double::intValue)
            .toList();
    }

    public Numbers(final List ints) {
        this.ints = ints;
    }
}
```

둘 다 generic type이 사라지고 raw type인 `List`로 변경된다. 동일한 signature를 가진 생성자가 2개 있으므로 컴파일 에러가 발생하는 것이다.

### 어떻게 해결할까?

일반적인 method라면 이름으로 구분하면 될 것이다. 그러나 생성자의 경우 method 이름을 바꿀 수 없다. 이 경우 정적 팩토링 패턴을 사용하면 이를 우회할 수 있다.

```java

public class Numbers {

    private List<Integer> ints;

    public Numbers(final List<Integer> ints) {
        this.ints = ints;
    }

    public static Numbers fromDoubles(final List<Double> doubles) {
        this(doubles.stream()
            .map(Double::intValue)
            .toList());
    }
}
```

## 참고 자료

[Type Erasure - Learning the Java Language](https://docs.oracle.com/javase/tutorial/java/generics/erasure.html)
