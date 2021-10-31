DROP TABLE IF EXISTS books;
DROP TABLE IF EXISTS authors;
DROP TABLE IF EXISTS signs;
DROP TABLE IF EXISTS signsofbooks;
DROP TABLE IF EXISTS discounts;
DROP TABLE IF EXISTS postponed;
DROP TABLE IF EXISTS cart;
DROP TABLE IF EXISTS genres;

/* "Книги" */
CREATE TABLE books
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    author      VARCHAR(250) NOT NULL,
    title       VARCHAR(250) NOT NULL,
    priceOld    VARCHAR(250) DEFAULT NULL,
    price       VARCHAR(250) DEFAULT NULL
);

/* "Авторы" */
create table authors
(
    id          INT,
    first_name  VARCHAR(50),
    last_name   VARCHAR(50)
);

/* Справочник "Виды признаков книг" */
create table signs
(
    id          INT,
    sign        VARCHAR(50)
);

/* "Признаки книг" */
create table signsofbooks
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    book_id     INT,
    signs_id    INT
);

/* "Скидки" */
create table discounts
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    book_id     INT,
    discount    INT /* Значение в процентах */
);

/* "Отложенные книги пользователей" */
create table postponed
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    book_id     INT,
    user_name   VARCHAR(250) NOT NULL
);

/* "Книги пользователей в корзине" */
create table cart
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    book_id     INT,
    user_name   VARCHAR(250) NOT NULL
);

/* "Жанры" */
CREATE TABLE genres
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    genre      VARCHAR(250) NOT NULL,
    parent_id  INT          NOT NULL,
    level      INT
);