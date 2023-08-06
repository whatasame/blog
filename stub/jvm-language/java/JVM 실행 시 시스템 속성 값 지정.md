# JVM 실행 시 시스템 속성 값 지정

## 어떻게 전달하나?

JVM을 시작할 때 -D를 붙여 system property를 전달할 수 있다. 예를 들면 다음과 같다.

```bash
java -jar -Dhello="Hello" app.jar
```

이를 Java 코드에서는 다음과 같이 사용할 수 있다.

```java
public class Main {

    public static void main(String[] args) {
        System.out.println("message = " + System.getProperty("hello"));
    }
}
```

## -D는 무엇을 의미할까?

출처가 불분명하지만 -D 옵션은 C/C++ 컴파일러에서 전처리 매크로를 사용하는 옵션에서 왔다고 한다. 이때 D는 `DEFINE`을 의미한다.

```bash
gcc hello.c -DGREETING="\"Hello, world\""
```

```cpp
#include <stdio.h>
int main(int argc, char* argv[]) {
    printf(GREETING);
    return 0;
}
```

## Spring 프로젝트에서 사용하기

application properties의 profile을 지정해주기 위해 다음과 같이 사용하였다.

```bash
-Dspring.profiles.active=dev 
```

위 옵션과 함께 JVM을 실행하면 dev profile을 적용한다.

## 참고 자료

[In Java -D what does the D stand for?](https://stackoverflow.com/questions/12518728/in-java-d-what-does-the-d-stand-for)

