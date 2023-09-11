# session B 시작
USE isolation_level_test;

SET TRANSACTION ISOLATION LEVEL REPEATABLE READ; # step 1. isolation level 수정

START TRANSACTION; # step 2. [트랜잭션 B 시작]

SELECT *
    FROM mice
    WHERE id = 2; # step 3. [트랜잭션 B 쿼리 실행] 2번 쥐 이름 = '시골쥐'

SELECT *
    FROM mice
    WHERE id = 2; # step 8. [트랜잭션 B 쿼리 실행] 2번 쥐 이름 = '시골쥐'

COMMIT; # step 9. [트랜잭션 B 종료]

