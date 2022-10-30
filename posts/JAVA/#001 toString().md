# #001 toString()

## 객체 정보를 출력해봅시다

학생 객체를 다루는 클래스를 아래와 같이 정의하겠습니다.

```java
public class Student {
    private int sid;      // 학번
    private String sname; // 이름
    private double grade; // 학점
}
```

생성자를 통해 Student 인스턴스를 생성하고 객체의 정보를 출력하기 위해 아래와 같은 코드를 작성하였습니다.

```java
public static void main(String[] args) {
        Student student = new Student();

        System.out.println(
                "sid : " + student.sid + "\n" +
                "sname : " + student.sname + "\n" +
                "grade : " + student.grade
        );
}
```

위 코드의 결과는 아래와 같습니다.

<p align="center"><img src="../../images/JAVA/#001 toString()-Untitled.png"></p>

그런데 위와 같은 코드를 매번 작성하는 것은 귀찮습니다. 별도의 함수를 만들어 사용해야 할까요?

아니면, **객체의 정보를 출력할 때 `System.out.println()`에 객체만 넣어서 출력할 순 없을까요?** 아래와 코드처럼 말이죠.

```java
public static void main(String[] args) {
        Student student = new Student();
        System.out.println(student);
}
```

위 코드의 결과는 아래와 같습니다.

<p align="center"><img src="../../images/JAVA/#001 toString()-Untitled 1.png"></p>

알기 힘든 결과가 나옵니다. Student는 student의 클래스 명인 것 같은데 75b84c92는 뭘까요?

사실, Java에선 객체를 단독으로 출력에 사용하면 자동으로 toString()을 호출합니다. 따라서 `System.out.println(student)`을 실행하면 student의 `toString()`가 실행되고, `toString()`에서 반환된 값이 표준 출력으로 나타나는 것입니다. 

그런데 Student 클래스에 `toString()`를 선언하지 않았습니다. 어떻게 된 것일까요?

## toString()이란

`toString()` 는 객체를 문자열로 표현한 문자열을 리턴하는 메서드입니다. `toString()`는 Object 클래스에 선언되어있습니다. 

<p align="center"><img src="../../images/JAVA/#001 toString()-Untitled 2.png"></p>

Object 클래스는 최상위 클래스로서 모든 클래스는 Object 클래스의 상속을 받습니다. Student 클래스도 Object 클래스의 상속을 받는 것이죠. 그렇기 때문에 우리가 정의하지 않은 `toString()`을 사용할 수 있는 것입니다.

<p align="center"><img src="../../images/JAVA/#001 toString()-Untitled 3.png"></p>

그럼 앞서 봤던 `toString()`의 결과는 Object 클래스에서 정의한 값이 출력된 것임을 알 수 있습니다. Object 클래스의 `toString()` 는 아래와 같이 구현되어 있습니다.

<p align="center"><img src="../../images/JAVA/#001 toString()-Untitled 4.png"></p>

이제야 위 결과가 어떤 값인지 알 것 같습니다. 예상했던 대로 Student는 객체의 클래스 이름이었고, 75b84c92는 객체의 해쉬코드 값이었습니다. 

우리의 목표는 `System.out.println()`에 객체만 넣어서 객체 정보를 출력하는 것이었습니다. 따라서 `toString()`을 우리 입맛대로 변경해보겠습니다.

## toString() 재정의

Java에서는 상속을 받은 메서드를 다시 정의하는 것이 가능합니다. 이를 Override라고 합니다. Student 클래스는 Object로부터 `toString()`을 상속받았으므로 Override할 수 있습니다. 아래와 같이 말이죠.

```java
@Override
public String toString() {
    return "Student{" +
            "sid=" + sid +
            ", sname='" + sname + '\'' +
            ", grade=" + grade +
            '}';
}
```

> 사실, 위 `toString()`은 제가 직접 작성한 것이 아닙니다. Intellij와 같은 통합 개발 환경(IDE)에선 `toString()`과 같이 자주 재정의되는 함수를 쉽게 생성할 수 있는 기능이 있습니다.
> 

이후, 앞에서 실행한 코드를 수정 없이 실행해보겠습니다.

```java
public static void main(String[] args) {
    Student student = new Student();
    System.out.println(student);
}
```

위 코드의 실행 결과는 아래와 같습니다.

<p align="center"><img src="../../images/JAVA/#001 toString()-Untitled 5.png"></p>

우리가 원했던 것처럼 `System.out.println()`에 객체만 입력하여 객체의 정보를 출력하였습니다. 이제부턴 `toString()`을 수정하여 객체 정보를 출력할 수 있습니다.