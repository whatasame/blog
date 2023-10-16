describe(`동일성, 동등성 연산자 학습 테스트`, () => {

  test(`==를 이용하여 데이터가 동등한지 확인한다.`, () => {
    /* given */
    const num = 3;
    const str = '3';

    /* when & then */
    expect(num == str).toBeTruthy();
  });

  test(`===를 이용하여 데이터가 동일한지 확인한다.`, () => {
    /* given */
    const num = 3;
    const str = '3';

    /* when & then */
    expect(num === str).toBeFalsy();
    expect(num === Number(str)).toBeTruthy();
  });
});
