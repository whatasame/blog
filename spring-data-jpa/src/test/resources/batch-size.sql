INSERT INTO `owners` (id, name)
VALUES (1, '솔라'),
       (2, '브라운'),
       (3, '네오');

INSERT INTO `restaurants` (id, name, owner_id)
VALUES (1, '치킨집', 1),
       (2, '피자집', 2),
       (3, '햄버거집', 3);

INSERT INTO `menus` (id, name, restaurant_id)
VALUES (1, '음치킨', 1),
       (2, '윙치킨', 1),
       (3, '봉치킨', 1),
       (4, '포테이토피자', 2),
       (5, '불고기피자', 2),
       (6, '책피자', 2),
       (7, '베이컨토마토디럭스', 3),
       (8, '맥스파이시', 3),
       (9, '빅맥', 3);

