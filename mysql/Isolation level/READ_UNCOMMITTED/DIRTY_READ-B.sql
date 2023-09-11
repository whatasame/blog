# session B 시작
USE isolation_level_test;

SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED; # step 4. isolation level 수정

START TRANSACTION; # step 5. [트랜잭션 B 시작]

SELECT *
    FROM mice
    WHERE id = 2; # step 6. [트랜잭션 B 쿼리 실행] 커밋되지 않은 값 조회

COMMIT; # step 7. [트랜잭션 B 종료] 커밋
