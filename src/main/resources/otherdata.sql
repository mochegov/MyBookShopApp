/* Справочник "Виды признаков книг" */
insert into signs (id, sign) values (1, 'Рекомендуемое');
insert into signs (id, sign) values (2, 'Новинки');
insert into signs (id, sign) values (3, 'Популярное');
insert into signs (id, sign) values (4, 'Бестселлеры');

/* Признаки книг - Рекомендуемые */
insert into signsofbooks (book_id, signs_id) values (1, 1);
insert into signsofbooks (book_id, signs_id) values (3, 1);
insert into signsofbooks (book_id, signs_id) values (5, 1);
insert into signsofbooks (book_id, signs_id) values (7, 1);
insert into signsofbooks (book_id, signs_id) values (9, 1);
insert into signsofbooks (book_id, signs_id) values (11, 1);
insert into signsofbooks (book_id, signs_id) values (13, 1);
insert into signsofbooks (book_id, signs_id) values (15, 1);

/* Признаки книг - Новинки */
insert into signsofbooks (book_id, signs_id) values (2, 2);
insert into signsofbooks (book_id, signs_id) values (4, 2);
insert into signsofbooks (book_id, signs_id) values (6, 2);
insert into signsofbooks (book_id, signs_id) values (8, 2);
insert into signsofbooks (book_id, signs_id) values (10, 2);
insert into signsofbooks (book_id, signs_id) values (12, 2);
insert into signsofbooks (book_id, signs_id) values (14, 2);
insert into signsofbooks (book_id, signs_id) values (16, 2);
insert into signsofbooks (book_id, signs_id) values (18, 2);
insert into signsofbooks (book_id, signs_id) values (20, 2);
insert into signsofbooks (book_id, signs_id) values (22, 2);

/* Признаки книг - Популярные */
insert into signsofbooks (book_id, signs_id) values (1, 3);
insert into signsofbooks (book_id, signs_id) values (2, 3);
insert into signsofbooks (book_id, signs_id) values (5, 3);
insert into signsofbooks (book_id, signs_id) values (7, 3);
insert into signsofbooks (book_id, signs_id) values (8, 3);
insert into signsofbooks (book_id, signs_id) values (9, 3);
insert into signsofbooks (book_id, signs_id) values (15, 3);
insert into signsofbooks (book_id, signs_id) values (16, 3);
insert into signsofbooks (book_id, signs_id) values (17, 3);
insert into signsofbooks (book_id, signs_id) values (20, 3);
insert into signsofbooks (book_id, signs_id) values (23, 3);
insert into signsofbooks (book_id, signs_id) values (24, 3);
insert into signsofbooks (book_id, signs_id) values (25, 3);

/* Признаки книг - Бестселлеры */
insert into signsofbooks (book_id, signs_id) values (2, 4);
insert into signsofbooks (book_id, signs_id) values (6, 4);
insert into signsofbooks (book_id, signs_id) values (11, 4);
insert into signsofbooks (book_id, signs_id) values (17, 4);
insert into signsofbooks (book_id, signs_id) values (21, 4);
insert into signsofbooks (book_id, signs_id) values (25, 4);

/* Скидки на книги */
insert into discounts (book_id, discount) values (2, 15);
insert into discounts (book_id, discount) values (5, 20);
insert into discounts (book_id, discount) values (8, 30);
insert into discounts (book_id, discount) values (11, 10);
insert into discounts (book_id, discount) values (15, 10);
insert into discounts (book_id, discount) values (18, 5);
insert into discounts (book_id, discount) values (22, 5);
insert into discounts (book_id, discount) values (24, 30);
insert into discounts (book_id, discount) values (25, 25);

/* Отложенные книги пользователей */
insert into postponed (book_id, user_name) values (2, 'mochegov@gmail.com');
insert into postponed (book_id, user_name) values (8, 'mochegov@gmail.com');
insert into postponed (book_id, user_name) values (10, 'mochegov@gmail.com');
insert into postponed (book_id, user_name) values (20, 'mochegov@gmail.com');
insert into postponed (book_id, user_name) values (23, 'mochegov@gmail.com');

/* Книги пользователей в корзине */
insert into cart (book_id, user_name) values (1, 'mochegov@gmail.com');
insert into cart (book_id, user_name) values (7, 'mochegov@gmail.com');
insert into cart (book_id, user_name) values (9, 'mochegov@gmail.com');
insert into cart (book_id, user_name) values (19, 'mochegov@gmail.com');
insert into cart (book_id, user_name) values (22, 'mochegov@gmail.com');

/* "Жанры" */
insert into genres (id, genre, parent_id, level) values (1,  'Лёгкое чтение',                               0, 1);
insert into genres (id, genre, parent_id, level) values (2,  'Фантастика',                                  1, 2);
insert into genres (id, genre, parent_id, level) values (3,  'Боевики',                                     1, 2);
insert into genres (id, genre, parent_id, level) values (4,  'Детективы',                                   1, 2);
insert into genres (id, genre, parent_id, level) values (5,  'Триллер',                                     4, 3);
insert into genres (id, genre, parent_id, level) values (6,  'Крутой детектив',                             4, 3);
insert into genres (id, genre, parent_id, level) values (7,  'Иронический детектив',                        4, 3);
insert into genres (id, genre, parent_id, level) values (8,  'Про маньяков',                                4, 3);
insert into genres (id, genre, parent_id, level) values (9,  'Шпионский детектив',                          4, 3);
insert into genres (id, genre, parent_id, level) values (10, 'Криминальный детектив',                       4, 3);
insert into genres (id, genre, parent_id, level) values (11, 'Классический детектив',                       4, 3);
insert into genres (id, genre, parent_id, level) values (12, 'Политический детектив',                       4, 3);
insert into genres (id, genre, parent_id, level) values (13, 'Фэнтези',                                     1, 2);
insert into genres (id, genre, parent_id, level) values (14, 'Романы',                                      1, 2);
insert into genres (id, genre, parent_id, level) values (15, 'Ужасы',                                       1, 2);
insert into genres (id, genre, parent_id, level) values (16, 'Приключения',                                 1, 2);
insert into genres (id, genre, parent_id, level) values (17, 'Серьёзное чтение',                            0, 1);
insert into genres (id, genre, parent_id, level) values (18, 'Биографии',                                  17, 2);
insert into genres (id, genre, parent_id, level) values (19, 'Деловая литература',                          0, 1);
insert into genres (id, genre, parent_id, level) values (20, 'Управление экономикой',                      19, 2);
insert into genres (id, genre, parent_id, level) values (21, 'Карьера',                                    19, 2);
insert into genres (id, genre, parent_id, level) values (22, 'Маркетинг, PR, реклама',                     19, 2);
insert into genres (id, genre, parent_id, level) values (23, 'Финансы',                                    19, 2);
insert into genres (id, genre, parent_id, level) values (24, 'Бизнес-справочники',                         19, 2);
insert into genres (id, genre, parent_id, level) values (25, 'Личные финансы',                             19, 2);
insert into genres (id, genre, parent_id, level) values (26, 'Менеджмент',                                 19, 2);
insert into genres (id, genre, parent_id, level) values (27, 'Зарубежная деловая литература',              19, 2);
insert into genres (id, genre, parent_id, level) values (28, 'Личная эффективность',                       19, 2);
insert into genres (id, genre, parent_id, level) values (29, 'Тайм-менеджмент',                            19, 2);
insert into genres (id, genre, parent_id, level) values (30, 'Малый бизнес',                               19, 2);
insert into genres (id, genre, parent_id, level) values (31, 'Продажи',                                    19, 2);
insert into genres (id, genre, parent_id, level) values (32, 'Стартапы и создание бизнеса',                19, 2);
insert into genres (id, genre, parent_id, level) values (33, 'Корпоративная культура',                     19, 2);
insert into genres (id, genre, parent_id, level) values (34, 'Банковское дело',                            19, 2);
insert into genres (id, genre, parent_id, level) values (35, 'Логистика',                                  19, 2);
insert into genres (id, genre, parent_id, level) values (36, 'Недвижимость',                               19, 2);
insert into genres (id, genre, parent_id, level) values (37, 'Интернет-бизнес',                            19, 2);
insert into genres (id, genre, parent_id, level) values (38, 'Ораторское искусство / риторика',            19, 2);
insert into genres (id, genre, parent_id, level) values (39, 'Привлечение клиентов и лояльность',          19, 2);
insert into genres (id, genre, parent_id, level) values (40, 'Делопроизводство',                           19, 2);
insert into genres (id, genre, parent_id, level) values (41, 'Переговоры',                                 19, 2);
insert into genres (id, genre, parent_id, level) values (42, 'Государственное и муниципальное управление', 19, 2);
insert into genres (id, genre, parent_id, level) values (43, 'О бизнесе популярно',                        19, 2);
insert into genres (id, genre, parent_id, level) values (44, 'Ценные бумаги / инвестиции',                 19, 2);
insert into genres (id, genre, parent_id, level) values (45, 'Бухучет / налогообложение / аудит',          19, 2);
insert into genres (id, genre, parent_id, level) values (46, 'Бухгалтерский учет в коммерческих банках',   45, 3);
insert into genres (id, genre, parent_id, level) values (47, 'Бухгалтерский учет на предприятиях',         45, 3);
insert into genres (id, genre, parent_id, level) values (48, 'Налоги',                                     45, 3);
insert into genres (id, genre, parent_id, level) values (49, 'Аудит',                                      45, 3);
insert into genres (id, genre, parent_id, level) values (50, 'Российская практика',                        19, 2);
insert into genres (id, genre, parent_id, level) values (51, 'Истории успеха',                             19, 2);
insert into genres (id, genre, parent_id, level) values (52, 'Интернет-маркетинг',                         19, 2);
insert into genres (id, genre, parent_id, level) values (53, 'Лидерство',                                  19, 2);
insert into genres (id, genre, parent_id, level) values (54, 'Управление бизнесом',                        19, 2);
insert into genres (id, genre, parent_id, level) values (55, 'Драматургия',                                 0, 1);
insert into genres (id, genre, parent_id, level) values (56, 'Античная драма',                             55, 2);
insert into genres (id, genre, parent_id, level) values (57, 'Комедия',                                    55, 2);
insert into genres (id, genre, parent_id, level) values (58, 'Российские коммедии',                        57, 3);
insert into genres (id, genre, parent_id, level) values (59, 'Иностранные коммедии',                       57, 3);
insert into genres (id, genre, parent_id, level) values (60, 'Сценарий',                                   55, 2);
insert into genres (id, genre, parent_id, level) values (61, 'Драма, пьеса',                               55, 2);