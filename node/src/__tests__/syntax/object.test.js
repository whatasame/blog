describe(`객체 학습 테스트`, () => {

  test(`객체를 생성한다.`, () => {
    /* given */
    const student = {
      name: '홍길똥',
      age: 17
    }

    /* when & then */
    expect(student.name).toBe('홍길똥');
    expect(student.age).toBe(17);
  });

  test(`객체의 property를 동적으로 추가할 수 있다.`, () => {
    /* given */
    const student = {
      name: '홍길똥',
      age: 17
    }

    /* when */
    student.gpa = 4.3;
    student.major = '컴퓨터공학과';

    /* then */
    expect(student.gpa).toBe(4.3);
    expect(student.major).toBe('컴퓨터공학과');
  });

  test(`객체의 property를 동적으로 수정할 수 있다.`, () => {
    /* given */
    const student = {
      name: '홍길똥',
      age: 17
    }

    /* when */
    student.name = "김길똥"
    student["age"] = 18;

    /* then */
    expect(student.name).toBe('김길똥');
    expect(student.age).toBe(18);
  });

  test(`객체의 property를 동적으로 삭제할 수 있다.`, () => {
    /* given */
    const student = {
      name: '홍길똥',
      age: 17
    }

    /* when */
    delete student.age;

    /* then */
    expect(student.age).toBeUndefined();
  });
});
