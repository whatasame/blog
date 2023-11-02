describe(`String 학습 테스트`, () => {

  test(`backtick을 이용한 문자열 생성`, () => {
    /* given */
    const num = 777
    const str = `num = ${num}`

    /* when & then */
    expect(str).toBe("num = 777");
  });

  test(`리터럴로 선언한 문자열은 동일한 객체이다.`, () => {
    /* given */
    const str1 = "안녕하세요";
    const str2 = "안녕하세요";

    /* when & then */
    expect(str1).toBe(str2);
  });

  test(`Symbol()을 이용하여 생성한 문자열은 고유한 객체다.`, () => {
    /* given */
    const symbol1 = Symbol("안녕하세요");
    const symbol2 = Symbol("안녕하세요");

    /* when & then */
    expect(symbol1).not.toBe(symbol2);
  })
})
