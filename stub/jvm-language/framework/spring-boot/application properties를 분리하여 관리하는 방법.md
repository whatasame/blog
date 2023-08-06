# application properties를 분리하여 관리하는 방법

## profile

application properties에는 profile이라는 개념이 있다.

profile을 이용하면 원하는 profile로 전환하며 사용할 값들을 관리할 수 있다.

## profile을 하나의 파일에서 구분하는 방법

`---`를 이용하면 하나의 application.yml에서 profile을 구분하고 적용할 수 있다.

```yml
spring:
  profiles:
    active: dev # 개발용 profile을 사용한다.

---
# 운영용 profile
spring:
  config:
    activate:
      on-profile: prod
---
# 개발용 profile
spring:
  config:
    activate:
      on-profile: dev
```

## profile을 여러 파일로 구분하는 방법

application-{profile}.yml의 형식으로 파일명을 짓는다. 위에서 한 파일에 관리하던 profile을 다음과 같이 구분할 수 있다.

* application.yml
* application-prod.yml
* application-dev.yml

## profile을 선택하는 방법

### application.yml

application.yml에서 다음과 같이 작성하면 사용할 profile을 지정할 수 있다.

```yml
spring:
  profiles:
    active: dev
```

### IDE

Intellij와 같은 IDE에서 activate profile이나 VM option을 설정할 수
있다.

자세한
방법은 [IntelliJ (Ultimate & Community) 에서 스프링부트 active profile 설정하기](https://jojoldu.tistory.com/547)를
참고.

### terminal

다음과 같이 터미널에서 지정할 수도 있다.

```bash
java -jar -Dspring.profiles.active.on-profiles=dev app.jar
```

위 경우 dev profile을 적용한다.

## 참고 자료

[Baeldung - Spring Profiles](https://www.baeldung.com/spring-profiles)
