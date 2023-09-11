# session A 시작
USE isolation_level_test;

# dirty read not occurred
SET TRANSACTION ISOLATION LEVEL READ COMMITTED; # step1. isolation level 수정

START TRANSACTION; # step2. [트랜잭션 A 시작]

UPDATE mice
SET name = '시골쥐 A한테 다털렸죠?ㅋㅋㅋㅋㅋㅋㅋㅋㅋ'
    WHERE id = 2; # step3. [트랜잭션 A 쿼리 실행] 이름 수정

ROLLBACK; # step 8. [트랜잭션 A 종료] 어라 이게 아닌데

