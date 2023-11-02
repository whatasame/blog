DROP TABLE IF EXISTS sequences;

CREATE TABLE sequences
(
    name     varchar(32) PRIMARY KEY,
    next_val BIGINT NOT NULL DEFAULT 1
);

INSERT INTO sequences (name)
VALUES ('post_seq');
