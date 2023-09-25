# session A 시작
USE deadlock_test;

START TRANSACTION; # step 1. [트랜잭션 A] 시작

SELECT * FROM mice WHERE id = 1 FOR UPDATE; # step 2. [트랜잭션 A] id = 1인 행에 대한 락 획득

UPDATE mice
SET name = '시골쥐 A한테 다털렸죠?'
    WHERE id = 2; # step 5. [트랜잭션 A] 트랜잭션 B가 획득한 id = 2 락이 해제될 때까지 대기

COMMIT; # step 8. [트랜잭션 A] undo 로그가 많은 트랜잭션은 계속 진행

SELECT * FROM mice; # step 9. [트랜잭션 A] 결과 확인
