DROP PROCEDURE IF EXISTS generate_post_id^;

CREATE PROCEDURE generate_post_id(IN user_id BIGINT, OUT post_id BIGINT)
BEGIN
    SET @service_start_time = UNIX_TIMESTAMP('2023-01-01 00:00:00.000');
    SET @shard_count = 1024;
    SET @sequence_name = 'post_seq';

    /* 부호(1bit) + 시간(43bit, 약 278년), + 샤드 ID (10bit)*/
    SET post_id = (
            FLOOR((UNIX_TIMESTAMP(NOW(3)) - @service_start_time) * 1000) << 20 |
            (user_id % @shard_count) << 10
        );

    /* 시퀀스 값 가져오기 (10bit) */
    SET post_id = post_id | (SELECT next_val % 1024 FROM sequences WHERE name = @sequence_name);
    UPDATE sequences SET next_val = next_val + 1 WHERE name = @sequence_name;
END^;

