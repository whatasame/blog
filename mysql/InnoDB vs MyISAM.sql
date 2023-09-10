# 데이터베이스 생성
DROP DATABASE IF EXISTS transaction_test;

CREATE DATABASE transaction_test;

USE transaction_test;

# InnoDB 데이터베이스 테스트
CREATE TABLE inno_db_test (
    id   BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(20)
) ENGINE = InnoDB;

INSERT INTO inno_db_test
VALUES (3, 'tommy');

INSERT INTO inno_db_test
VALUES (1, 'neo'),
       (2, 'b'),
       (3, 'tommy');

SELECT *
FROM inno_db_test;

# MyISAM 데이터베이스 테스트
CREATE TABLE myisam_test (
    id   BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(20)
) ENGINE = MyISAM;

INSERT INTO myisam_test
VALUES (3, 'tommy');

INSERT INTO myisam_test
VALUES (1, 'neo'),
       (2, 'brie'),
       (3, 'tommy');

SELECT *
FROM myisam_test;

INSERT INTO myisam_test
VALUES (3, 'tommy'),
       (4, 'gugu'),
       (5, 'solar');

SELECT *
FROM myisam_test;

INSERT INTO myisam_test
VALUES (4, 'gugu'),
       (5, 'solar'),
       (3, 'tommy'),
       (6, 'brown');

SELECT *
FROM myisam_test;

# auto commit 확인
SHOW VARIABLES LIKE 'autocommit';
