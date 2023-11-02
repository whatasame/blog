describe(`반복문 학습 테스트`, () => {

  test(`for of를 이용하여 Iterable 객체을 순회한다.`, () => {
    /* given */
    const arr = [1, 2, 3];

    /* when */
    const values = [];
    for (const value of arr) {
      values.push(value);
    }

    /* then */
    expect(values).toEqual([1, 2, 3]);
  });

  test(`for in을 이용하여 객체의 프로퍼티를 순회한다.`, () => {
    /* given */
    const obj = {
      a: 1,
      b: 2,
      c: 3
    }

    /* when */
    const keys = [];
    for (const key in obj) {
      keys.push(key);
    }

    /* then */
    expect(keys).toEqual(['a', 'b', 'c']);
  });
});
