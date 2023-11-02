const {Worker, workerData} = require('worker_threads');

describe(`Worker Thread 학습 테스트`, () => {
  test(`Worker Thread를 생성한다.`, (done) => {
    /* given */
    const worker = new Worker('./src/thread/worker-todo.mjs', {
      workerData: {op1: 5, op2: 3}
    });

    /* when */
    worker.on('message', (message) => {
      console.log('[Worker 1] Result: ' + message);
    });

    worker.on('exit', () => {
      console.log('[Worker 1] exited');

      done(); // 비동기 작업이 완료되면 done() 함수를 호출하여 Jest에게 알린다.
    });

    /* then */
    // console.log 확인
    // - [Worker 1] Result: 15
    // - [Worker 1] exited
  });
});

