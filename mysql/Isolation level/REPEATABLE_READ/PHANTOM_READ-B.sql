# session B 시작
# MVCC에서 phantom read가 발생하는 경우를 재현하기 위해 step 8에서 Locking read를 사용
USE isolation_level_test;

SET TRANSACTION ISOLATION LEVEL REPEATABLE READ; # step1. isolation level 수정

START TRANSACTION; # step2. [트랜잭션 B 시작]

SELECT * FROM mice; # step3. [트랜잭션 B 쿼리 실행] 전체 조회 = ['도시쥐', '시골쥐']

SELECT * FROM mice FOR UPDATE; # step 8. [트랜잭션 B 쿼리 실행] 전체 조회 = ['도시쥐', '시골쥐', '해외쥐']

COMMIT; # step 9. [트랜잭션 B 종료]

