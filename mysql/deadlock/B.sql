# session B 시작
USE deadlock_test;

START TRANSACTION; # step 3. [트랜잭션 B] 시작

SELECT * FROM mice WHERE id = 2 FOR UPDATE; # step 4. [트랜잭션 B] id = 2인 행에 대해 락 획득

UPDATE mice
SET name = '서울쥐 B한테 다털렸죠?'
    WHERE id = 1; # step 6. [트랜잭션 B] ! 데드락 ! 트랜잭션 A가 획득한 id = 1 락이 해제될 때까지 대기

COMMIT; # step 7. [트랜잭션 B] InnoDB -> deadlock 감지 -> rollback 수행

SELECT * FROM mice; # step 8. [트랜잭션 B] 결과 확인
