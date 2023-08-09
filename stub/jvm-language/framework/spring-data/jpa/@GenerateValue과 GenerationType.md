# @GenerateValue과 GenerationType

JPA의 Entity에서 `@Id`를 붙이면 Entity의 필드를 Primary key로 지정할 수 있다.

이후 `@GenerateValue`를 이용하여 PK 값을 생성하는 전략을 지정해 줄 수 있다.

## GenerationType

GenerationType은 PK 값 생성 전략을 나타낸다. 기본 값은 AUTO이다.

### AUTO

데이터베이스에 맞는 전략을 선택한다. 따라서 데이터베이스가 존재하거나 JPA가 데이터베이스를 만들 수 있어야한다.

### IDENTITY

Entity PK를 데이터베이스 ID 컬럼을 이용하여 생성한다.

## 실험해보기

application properties에서 jpa ddl 옵션을 다음과 같이 적용하여 데이터베이스도 존재하지 않고, 만들 수도 없도록 했다.

```yml
spring:
  jpa:
    hibernate:
      ddl-auto: none # default: create-drop
```

### AUTO의 경우

![](https://github.com/whatasame/TIL/assets/97666463/46075f6c-de9d-464a-ba1b-f61b0be707f3)

데이터베이스가 존재하지 않고 데이터베이스를 만들지도 못하므로 예외가 발생한다.

### IDENTITY의 경우

![](https://github.com/whatasame/TIL/assets/97666463/219fc471-070b-410a-92d0-1a0bef9a0142)

영속화를 위해 데이터베이스 테이블의 ID column을 이용해야하는데 테이블이 존재하지 않아 예외가 발생한다.

## 참고 자료

[Enum GenerationType](https://docs.jboss.org/hibernate/jpa/2.1/api/javax/persistence/GenerationType.html)
