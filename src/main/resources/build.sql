CREATE SEQUENCE customer_serial INCREMENT BY 2 MINVALUE 50 START 100 OWNED BY customer.id_customer;
CREATE TABLE customer (
	id_customer     integer ,
    first_name      varchar(140) DEFAULT 'Ivan',
    last_name       varchar(140) DEFAULT 'Ivanov',
    modtime         timestamp DEFAULT current_timestamp
);
ALTER TABLE customer ALTER COLUMN id_customer SET DEFAULT  nextval('customer_serial');

CREATE TABLE actor (
	id_actor        integer ,
    first_name      varchar(140) DEFAULT 'Alexey',
    last_name       varchar(140) DEFAULT 'Alexeyev',
    modtime         timestamp DEFAULT current_timestamp
);
 CREATE SEQUENCE actor_serial INCREMENT BY 2 MINVALUE 50 START 101 OWNED BY actor.id_actor;
ALTER TABLE actor ALTER COLUMN id_actor SET DEFAULT  nextval('actor_serial');

INSERT INTO customer DEFAULT VALUES;
INSERT INTO customer(first_name) VALUES('Vladimir');
INSERT INTO customer(first_name, last_name) VALUES('Evgeniy', 'Evgenev');
INSERT INTO customer(first_name, last_name) VALUES('Evgeniy', 'Smirnov');
INSERT INTO customer(first_name, last_name) VALUES('Dmitriy', 'Smirnov');
INSERT INTO customer(first_name, last_name) VALUES('Mihail', 'Durov');
INSERT INTO customer(first_name, last_name) VALUES('Mihail', '"Alexeyev"');
UPDATE customer SET last_name = 'Alexeyev' WHERE last_name = '"Alexeyev"';

INSERT INTO actor DEFAULT VALUES;
INSERT INTO actor(first_name, last_name) VALUES('Mihail', 'Ivanov');
INSERT INTO actor(first_name, last_name) VALUES('Mihail', 'Durov');
INSERT INTO actor(first_name, last_name) VALUES('Ferdor', 'Ivanov');
INSERT INTO actor(first_name, last_name) VALUES('Denis', 'Smirnov');

-- Простое объединение под общими столбцами с идинаковыми типами.
SELECT first_name, last_name FROM customer
UNION
SELECT first_name, last_name FROM actor;

-- Простое объединение под общими столбцами с идинаковыми типами.
SELECT id_customer as id, first_name as name1, last_name as name2 FROM customer
UNION
SELECT id_actor, first_name, last_name FROM actor
ORDER BY id;

-- Возвращает одинаковые значения без дубликатов и без учета дубликатов
SELECT last_name AS name2 FROM customer
INTERSECT
SELECT last_name FROM actor;

-- Возвращает одинаковые значения без дубликатов и без учета дубликатов
SELECT first_name as name1 FROM customer
INTERSECT
SELECT first_name FROM actor;

-- Возвращает одинаковые значения без дубликатов и без учета дубликатов
SELECT first_name, last_name FROM customer
INTERSECT
SELECT first_name, last_name FROM actor;

-- Так как id не совпадают то просто выводится таблица customer, она сдесь слева
SELECT id_customer as id, first_name as name1, last_name as name2 FROM customer
EXCEPT
SELECT id_actor, first_name, last_name FROM actor
ORDER BY id;

-- выводятся все пары first_name last_name из customer которых нет в actor
SELECT first_name as name1, last_name as name2 FROM customer
EXCEPT
SELECT first_name, last_name FROM actor ORDER BY name1;

-- выводятся все last_name из customer которых нет в actor
SELECT last_name as name2 FROM customer
EXCEPT
SELECT last_name FROM actor;

-- выводятся все first_name из customer которых нет в actor без дубликатов и без учета дубликатов
SELECT first_name as name1 FROM customer
EXCEPT
SELECT first_name FROM actor;

-- берется каждый элемент из левой стороны, и соединяется с каждым элементом с правой стороны.
SELECT * FROM customer
CROSS JOIN actor;

-- частный случай CROSS JOIN но для тех строк где соблюдается условие
SELECT * FROM customer c
INNER JOIN actor a
ON c.first_name = a.first_name;

-- частный случай CROSS JOIN но для тех строк где соблюдается условие
SELECT * FROM customer c
INNER JOIN actor a
ON c.last_name = a.last_name;

-- INNER JOIN симметрична
SELECT * FROM actor a
INNER JOIN customer c
ON c.last_name = a.last_name;
