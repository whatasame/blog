# session B 시작
USE isolation_level_test;

# dirty read not occurred
SET TRANSACTION ISOLATION LEVEL READ COMMITTED; # step 4. isolation level 수정

START TRANSACTION; # step 5. [트랜잭션 B 시작]

SELECT *
    FROM mice
    WHERE id = 2; # step 6. [트랜잭션 B 쿼리 실행] 트랜잭션 A에서 변경한 값을 조회하지 않음

COMMIT; # step 7. [트랜잭션 B 종료] 응 바꾸던가 ㅋㅋ
