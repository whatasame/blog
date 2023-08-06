# Facade

## 퍼사드 패턴이란

![](https://upload.wikimedia.org/wikipedia/commons/thumb/0/05/Basilica_di_San_Pietro_%2815042367249%29.jpg/440px-Basilica_di_San_Pietro_%2815042367249%29.jpg)

직역하면 건물의 앞면을 뜻하며 디자인 패턴의 생성 패턴 중 하나이다.

해결해야하는 비즈니스 로직이 라이브러리와 같은 외부 구현 사항과 밀접하게 관련이 있을 때 사용한다.

수십개의 기능이 있는 정교한 라이브러리 중 일부 기능만을 사용할 때 유용한 패턴이다.

## 내가 겪은 문제 상황

지하철 노선의 최단 경로를 구하기 위하여 [JGraphT](https://jgrapht.org/) 라이브러리를 이용했다. 문제는
도메인 [StationGraph](https://github.com/whatasame/spring-subway/commit/4b49616590622ffa9dc390855bc675ded5a1a996#diff-2397982933839d407067d2c8e77086132d01fd4979b9960849e9b3de1f13c342)
에서
해당 라이브러리에 의존하고 있었기 때문에 업데이트 등의
이유로 라이브러리가 변경되면 도메인도 변경해야한다.

> 의존관걔를 쉽게 파악하는 방법은 import 패키지를 보면 된다. StationGraph를 보면 JGraphT를 **완전** 의존하고 있다.

## 어떻게 해결할 수 있을까?

Facade 패턴을 적용하여 도메인과 라이브러리를 잇는 중간 객체를 둔다.

## 실제 코드로 감 익히기

[예제 코드](https://refactoring.guru/ko/design-patterns/facade/java/example#example-0--facade)에서 동영상을
변환하는 **복잡한 라이브러리**를 직접 사용하는 `VideoConversionFacade`를 정의하고 도메인에서는 **복잡한 라이브러리**가
아닌 `VideoConversionFacade`를
의존한다.

## 참고자료

[퍼사드 패턴](https://refactoring.guru/ko/design-patterns/facade)

[Façade](https://en.wikipedia.org/wiki/Fa%C3%A7ade)

[Step4 경로 조회 기능 구현 #61](https://github.com/next-step/spring-subway/pull/61#review-thread-or-comment-id-817685558)
