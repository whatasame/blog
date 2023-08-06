# Stream API를 이용하여 Map을 생성하는 방법

## 주로 사용하는 방법

특정 객체 내부의 값을 key로, 객체를 value로 구성된 Map을 Stream API를 이용하여 만드는 방법은 다음과 같다.

```java
public class StreamMap {

    public static void main(String[] args) {
        final List<Member> memberList = memberRepository.findAll();

        final Map<String, Member> nameByMember = memberList.stream()
            .collect(Collectors.toMap(Member::getName, Function.identity()));
    }
}
```

## Map 작명 방법

valueByKey로 짓는다. 예를 들어 key가 이름이고 value가 회원이면 `memberByName`
