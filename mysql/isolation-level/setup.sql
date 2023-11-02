# ---------- 테이블 생성 및 초기 데이터 설정 ----------
DROP DATABASE IF EXISTS isolation_level_test;

CREATE DATABASE isolation_level_test;

USE isolation_level_test;

CREATE TABLE mice (
    id   INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL
);

INSERT INTO mice (id, name)
VALUES (1, '서울쥐'),
       (2, '시골쥐');

SELECT *
FROM mice;
