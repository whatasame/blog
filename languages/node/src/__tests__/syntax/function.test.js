describe(`함수 학습 테스트`, () => {

  test(`hoisting 테스트`, () => {
    /* given */
    function func() {
      return 1;
    }

    /* when & then */
    expect(func()).toBe(777);

    /* hoisting */
    function func() {
      return 777;
    }
  });

  test(`객체의 함수를 호출하면 this는 호출한 객체를 가리킨다.`, () => {
    /* given */
    const obj = {
      value: '객췌',
      func: function () {
        return this.value;
      }
    }

    /* when & then */
    expect(obj.func()).toBe('객췌');
  });

  test(`객체의 함수를 외부에서 호출하면 this는 undefined를 가리킨다.`, () => {
    /* given */
    const obj = {
      value: '객췌',
      func: function () {
        return this.value;
      }
    }

    /* when & then */
    expect(obj.func.call(undefined)).toBeUndefined();
  });

  test(`객체의 함수를 외부에서 호출하되 객체를 전달하면 this는 전달한 객체를 가리킨다.`, () => {
    /* given */
    const obj = {
      value: '객췌',
      func: function () {
        return this.value;
      }
    }

    /* when & then */
    expect(obj.func.call(obj)).toBe('객췌');
  });

  test(`화살표 함수는 this를 바인딩하지 않는다.`, () => {
    /* given */
    const obj = {
      value: '객췌',
      func: () => {
        return this.value;
      }
    }

    /* when & then */
    expect(obj.func()).toBeUndefined();
    expect(obj.func.call(obj)).toBeUndefined();
  });
});
