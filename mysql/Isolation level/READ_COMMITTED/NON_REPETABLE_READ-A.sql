# session A 시작
USE isolation_level_test;

SET TRANSACTION ISOLATION LEVEL READ COMMITTED; # step 4. isolation level 수정

START TRANSACTION; # step 5. [트랜잭션 A 시작]

UPDATE mice
SET name = '시골쥐 A한테 다털렸죠?ㅋㅋㅋㅋㅋㅋㅋㅋㅋ'
    WHERE id = 2; # step 6. [트랜잭션 A 쿼리 실행] 이름 수정

COMMIT; # step 7. [트랜잭션 A 종료]

