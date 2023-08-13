# Annotation

## annotation 사용법

Spring Web에서 사용하는 `@RequestMapping`이 어떻게 동작하는지 궁금해서 알아봤다.

```java

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Mapping
public @interface RequestMapping {

    String name() default "";

    @AliasFor("path")
    String[] value() default {};

    @AliasFor("value")
    String[] path() default {};

    RequestMethod[] method() default {};

    String[] params() default {};

    String[] headers() default {};

    String[] consumes() default {};

    String[] produces() default {};

}

```

### @Target

annotation을 사용할 수 있는 범위를 지정한다.

### @Rentention

annotation이 유지되는 생명 주기를 결정한다. RUNTIME이 아닐 경우 runtime에서 annotation 정보를 얻을 수 없거나 사라진다.

### element

annotation를 구성하는 데이터 컴포넌트로 element name으로 접근할 수 있다. 단, 이름이 value인 element name은 별다른 이름 지정 없이 사용할 수
있다.

## TODO

### 알아볼 것

* @Documented
* @Mapping
* @AliasFor
