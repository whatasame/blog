describe(`모듈 학습 테스트`, () => {

  test(`CommonJS 방식으로 블럭 내에서 모듈을 가져온다.`, () => {
    /* given */
    const {odd, even} = require('../../module/common-js/variable');
    const checkOddOrEven = require('../../module/common-js/function');

    /* when & then */
    expect(odd).toBe('홀수입니다.');
    expect(even).toBe('짝수입니다.');
    expect(checkOddOrEven(3)).toBe('홀수입니다.');
  });
});

