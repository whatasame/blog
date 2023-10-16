describe(`자료형 학습 테스트`, () => {

  test(`typeof 연산자를 이용하여 데이터의 타입을 확인한다.`, () => {
    /* given */
    const num = 3;
    const str = '3';
    const obj = {};
    const arr = [];
    const func = () => {
    };

    /* when & then */
    expect(typeof num).toBe('number');
    expect(typeof str).toBe('string');
    expect(typeof obj).toBe('object');
    expect(typeof arr).toBe('object');
    expect(typeof func).toBe('function');
  });

  test(`자료형 변환 함수를 이용하여 자료형을 변경한다.`, () => {
    /* given */
    const num = 3;

    /* when & then */
    expect(typeof num).toBe('number');
    expect(typeof String(num)).toBe('string');
    expect(typeof Boolean(num)).toBe('boolean');
    expect(typeof Number(num)).toBe('number');
  });

  test(`parseInt()를 이용하여 정수로 변환한다.`, () => {
    /* given */
    const str = '17.6';

    /* when & then */
    expect(parseInt(str)).toBe(17);
  });

  test(`parseFloat()를 이용하여 실수로 변환한다.`, () => {
    /* given */
    const str = '3.141592';

    /* when & then */
    expect(parseFloat(str)).toBe(3.141592);
  });
});
