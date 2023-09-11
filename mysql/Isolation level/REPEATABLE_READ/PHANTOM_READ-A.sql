# session A 시작
USE isolation_level_test;

SET TRANSACTION ISOLATION LEVEL REPEATABLE READ; # step 4. isolation level 수정

START TRANSACTION; # step 5. [트랜잭션 A 시작]

INSERT INTO mice (id, name)
    VALUES (3, '외국쥐'); # step 6. [트랜잭션 A 쿼리 실행] 새로운 쥐 추가

COMMIT; # step 7. [트랜잭션 A 종료]
