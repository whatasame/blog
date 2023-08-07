# @SpringBootTeset webEnvironment

## webEnvironment

SpringBootTest에서 webEnvironment 값을 조작하여 테스트 환경에서 내장 톰캣 서버를 결정할 수 있다.

### WebEnvironment.MOCK

**webEnvironment 기본 값**이다.

Mock Servlet 서버를 띄운다. Servlet 서버를 띄우지 않기 때문에 포트 번호가 없다.

### WebEnvironment.DEFINED_PORT

application properties에 설정한 포트 번호로 내장된 Servlet 서버를 띄운다.

### WebEnvironment.RANDOM_PORT

임의의 포트 번호로 내장된 Servlet 서버를 띄운다.

### WebEnvironment.NONE

Servlet 서버를 띄우지 않는다.

## DEFINE_PORT와 RANDOM_PORT 사용 시 주의점

DEFINE_PORT와 RANDOM_PORT는 실제 내장 서버를 사용한다.

따라서 만약 테스트 코드 작성 시 인수 테스트 등 클라이언트에 해당하는 로직이 있을 경우 클라이언트와 서버는 서로 분리된 쓰레드에서 동작한다.

이 경우 서버에서 시작된 모든 트랜잭션은 롤백되지 않으니 주의하자.

## 참고 자료

[Enum Class SpringBootTest.WebEnvironment](https://docs.spring.io/spring-boot/docs/current/api/org/springframework/boot/test/context/SpringBootTest.WebEnvironment.html)

[46.3 Testing Spring Boot Applications](https://docs.spring.io/spring-boot/docs/2.1.5.RELEASE/reference/html/boot-features-testing.html#boot-features-testing-spring-boot-applications)
