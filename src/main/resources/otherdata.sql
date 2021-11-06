/* Книги */
insert into books (id, author, title, price_old, price, discount) values (1,  'Drucie Goodbourn', 'Exploit real-time e-commerce',  203,  43,  0);
insert into books (id, author, title, price_old, price, discount) values (2,  'Marcia Aggiss',    'Utilize real-time interfaces',  260,  35, 30);
insert into books (id, author, title, price_old, price, discount) values (3,  'Giovanni Bigg',    'Extend strategic e-services',   272, 151,  0);
insert into books (id, author, title, price_old, price, discount) values (4,  'Duffie Eynon',     'Iterate integrated solutions',  271, 141,  0);
insert into books (id, author, title, price_old, price, discount) values (5,  'Anselma McKennan', 'Cultivate magnetic e-commerce', 278,  54,  0);
insert into books (id, author, title, price_old, price, discount) values (6,  'Gert Kira',        'Architect vertical ROI',        282, 102,  0);
insert into books (id, author, title, price_old, price, discount) values (7,  'Clerc Djakovic',   'Disintermediate architectures', 295, 181,  0);
insert into books (id, author, title, price_old, price, discount) values (8,  'Stacee Elsy',      'Extend leading methodologies',  283,  97,  0);
insert into books (id, author, title, price_old, price, discount) values (9,  'Dorotea Bonhill',  'Integrate one-to-one systems',  258,  52,  0);
insert into books (id, author, title, price_old, price, discount) values (10, 'Minni Bunney',     'Revolutionize infrastructures', 260,  33, 20);
insert into books (id, author, title, price_old, price, discount) values (11, 'Berty Gandley',    'Enhance innovative e-services', 256, 160,  0);
insert into books (id, author, title, price_old, price, discount) values (12, 'Raynard Tesdale',  'Maximize 24/365 e-tailers',     262,  34, 10);
insert into books (id, author, title, price_old, price, discount) values (13, 'Joanna Hutfield',  'Scale integrated architect',    257,  32,  5);
insert into books (id, author, title, price_old, price, discount) values (14, 'Dannie Glidder',   'Enable frictionless services',  288,  90,  0);
insert into books (id, author, title, price_old, price, discount) values (15, 'Timmie Adshede',   'Strategize B2B channels',       232,  44,  0);
insert into books (id, author, title, price_old, price, discount) values (16, 'Fernando Neads',   'Syndicate deliverables',        287, 101,  0);
insert into books (id, author, title, price_old, price, discount) values (17, 'Teddy Guilliland', 'Utilize bleeding-edge users',   281, 146,  5);
insert into books (id, author, title, price_old, price, discount) values (18, 'Paloma Grebert',   'Cultivate 24/365 networks',     270, 184,  0);
insert into books (id, author, title, price_old, price, discount) values (19, 'Kristos Usher',    'Plug-and-play platforms',       278,  77, 30);
insert into books (id, author, title, price_old, price, discount) values (20, 'Darcey Rohmer',    'Target innovative paradigms',   298, 130, 30);
insert into books (id, author, title, price_old, price, discount) values (21, 'Cristie Isacsson', 'Generate open-source business', 276, 113,  0);
insert into books (id, author, title, price_old, price, discount) values (22, 'Lindsay Couthart', 'Implement user-centric market', 254, 115,  0);
insert into books (id, author, title, price_old, price, discount) values (23, 'Helyn Schneidar',  'Utilize supply-chains',         279,  77, 20);
insert into books (id, author, title, price_old, price, discount) values (24, 'Bette-ann Askem',  'Enable dot-com supply-chains',  297, 110,  0);
insert into books (id, author, title, price_old, price, discount) values (25, 'Dom Robuchon',     'Facilitate efficient eyeballs', 232, 131,  0);

/* Справочник "Виды признаков книг" */
insert into signs (id, sign_name) values (1, 'Рекомендуемое');
insert into signs (id, sign_name) values (2, 'Новинки');
insert into signs (id, sign_name) values (3, 'Популярное');
insert into signs (id, sign_name) values (4, 'Бестселлеры');

/* Признаки книг - Рекомендуемые */
insert into book_signes (book_id, sign_id) values (1,  1);
insert into book_signes (book_id, sign_id) values (3,  1);
insert into book_signes (book_id, sign_id) values (5,  1);
insert into book_signes (book_id, sign_id) values (7,  1);
insert into book_signes (book_id, sign_id) values (9,  1);
insert into book_signes (book_id, sign_id) values (11, 1);
insert into book_signes (book_id, sign_id) values (13, 1);
insert into book_signes (book_id, sign_id) values (15, 1);

/* Признаки книг - Новинки */
insert into book_signes (book_id, sign_id) values (2,  2);
insert into book_signes (book_id, sign_id) values (4,  2);
insert into book_signes (book_id, sign_id) values (6,  2);
insert into book_signes (book_id, sign_id) values (8,  2);
insert into book_signes (book_id, sign_id) values (10, 2);
insert into book_signes (book_id, sign_id) values (12, 2);
insert into book_signes (book_id, sign_id) values (14, 2);
insert into book_signes (book_id, sign_id) values (16, 2);
insert into book_signes (book_id, sign_id) values (18, 2);
insert into book_signes (book_id, sign_id) values (20, 2);
insert into book_signes (book_id, sign_id) values (22, 2);

/* Признаки книг - Популярные */
insert into book_signes (book_id, sign_id) values (1,  3);
insert into book_signes (book_id, sign_id) values (2,  3);
insert into book_signes (book_id, sign_id) values (5,  3);
insert into book_signes (book_id, sign_id) values (7,  3);
insert into book_signes (book_id, sign_id) values (8,  3);
insert into book_signes (book_id, sign_id) values (9,  3);
insert into book_signes (book_id, sign_id) values (15, 3);
insert into book_signes (book_id, sign_id) values (16, 3);
insert into book_signes (book_id, sign_id) values (17, 3);
insert into book_signes (book_id, sign_id) values (20, 3);
insert into book_signes (book_id, sign_id) values (23, 3);
insert into book_signes (book_id, sign_id) values (24, 3);
insert into book_signes (book_id, sign_id) values (25, 3);

/* Признаки книг - Бестселлеры */
insert into book_signes (book_id, sign_id) values (2, 4);
insert into book_signes (book_id, sign_id) values (6, 4);
insert into book_signes (book_id, sign_id) values (11, 4);
insert into book_signes (book_id, sign_id) values (17, 4);
insert into book_signes (book_id, sign_id) values (21, 4);
insert into book_signes (book_id, sign_id) values (25, 4);

/* Пользователи */
insert into users (id, username) values (1, 'mochegov@gmail.com');

/* Корзины пользователей */
insert into carts (id, user_id) values (1, 1);

/* Книги в корзинах пользователей */
insert into books_in_cart (book_id, cart_id) values (1,  1);
insert into books_in_cart (book_id, cart_id) values (7,  1);
insert into books_in_cart (book_id, cart_id) values (9,  1);
insert into books_in_cart (book_id, cart_id) values (19, 1);
insert into books_in_cart (book_id, cart_id) values (22, 1);

/* Список отложенных книг пользователя */
insert into postoned_books (id, user_id) values (1, 1);

/* Отложенные книги пользователей */
insert into books_postoned_of_user (book_id, postoned_books_id) values (2,  1);
insert into books_postoned_of_user (book_id, postoned_books_id) values (8,  1);
insert into books_postoned_of_user (book_id, postoned_books_id) values (10, 1);
insert into books_postoned_of_user (book_id, postoned_books_id) values (20, 1);
insert into books_postoned_of_user (book_id, postoned_books_id) values (23, 1);

/* "Жанры" */
insert into genres (id, name, parent_id, level) values (1,  'Лёгкое чтение',                               0, 1);
insert into genres (id, name, parent_id, level) values (2,  'Фантастика',                                  1, 2);
insert into genres (id, name, parent_id, level) values (3,  'Боевики',                                     1, 2);
insert into genres (id, name, parent_id, level) values (4,  'Детективы',                                   1, 2);
insert into genres (id, name, parent_id, level) values (5,  'Триллер',                                     4, 3);
insert into genres (id, name, parent_id, level) values (6,  'Крутой детектив',                             4, 3);
insert into genres (id, name, parent_id, level) values (7,  'Иронический детектив',                        4, 3);
insert into genres (id, name, parent_id, level) values (8,  'Про маньяков',                                4, 3);
insert into genres (id, name, parent_id, level) values (9,  'Шпионский детектив',                          4, 3);
insert into genres (id, name, parent_id, level) values (10, 'Криминальный детектив',                       4, 3);
insert into genres (id, name, parent_id, level) values (11, 'Классический детектив',                       4, 3);
insert into genres (id, name, parent_id, level) values (12, 'Политический детектив',                       4, 3);
insert into genres (id, name, parent_id, level) values (13, 'Фэнтези',                                     1, 2);
insert into genres (id, name, parent_id, level) values (14, 'Романы',                                      1, 2);
insert into genres (id, name, parent_id, level) values (15, 'Ужасы',                                       1, 2);
insert into genres (id, name, parent_id, level) values (16, 'Приключения',                                 1, 2);
insert into genres (id, name, parent_id, level) values (17, 'Серьёзное чтение',                            0, 1);
insert into genres (id, name, parent_id, level) values (18, 'Биографии',                                  17, 2);
insert into genres (id, name, parent_id, level) values (19, 'Деловая литература',                          0, 1);
insert into genres (id, name, parent_id, level) values (20, 'Управление экономикой',                      19, 2);
insert into genres (id, name, parent_id, level) values (21, 'Карьера',                                    19, 2);
insert into genres (id, name, parent_id, level) values (22, 'Маркетинг, PR, реклама',                     19, 2);
insert into genres (id, name, parent_id, level) values (23, 'Финансы',                                    19, 2);
insert into genres (id, name, parent_id, level) values (24, 'Бизнес-справочники',                         19, 2);
insert into genres (id, name, parent_id, level) values (25, 'Личные финансы',                             19, 2);
insert into genres (id, name, parent_id, level) values (26, 'Менеджмент',                                 19, 2);
insert into genres (id, name, parent_id, level) values (27, 'Зарубежная деловая литература',              19, 2);
insert into genres (id, name, parent_id, level) values (28, 'Личная эффективность',                       19, 2);
insert into genres (id, name, parent_id, level) values (29, 'Тайм-менеджмент',                            19, 2);
insert into genres (id, name, parent_id, level) values (30, 'Малый бизнес',                               19, 2);
insert into genres (id, name, parent_id, level) values (31, 'Продажи',                                    19, 2);
insert into genres (id, name, parent_id, level) values (32, 'Стартапы и создание бизнеса',                19, 2);
insert into genres (id, name, parent_id, level) values (33, 'Корпоративная культура',                     19, 2);
insert into genres (id, name, parent_id, level) values (34, 'Банковское дело',                            19, 2);
insert into genres (id, name, parent_id, level) values (35, 'Логистика',                                  19, 2);
insert into genres (id, name, parent_id, level) values (36, 'Недвижимость',                               19, 2);
insert into genres (id, name, parent_id, level) values (37, 'Интернет-бизнес',                            19, 2);
insert into genres (id, name, parent_id, level) values (38, 'Ораторское искусство / риторика',            19, 2);
insert into genres (id, name, parent_id, level) values (39, 'Привлечение клиентов и лояльность',          19, 2);
insert into genres (id, name, parent_id, level) values (40, 'Делопроизводство',                           19, 2);
insert into genres (id, name, parent_id, level) values (41, 'Переговоры',                                 19, 2);
insert into genres (id, name, parent_id, level) values (42, 'Государственное и муниципальное управление', 19, 2);
insert into genres (id, name, parent_id, level) values (43, 'О бизнесе популярно',                        19, 2);
insert into genres (id, name, parent_id, level) values (44, 'Ценные бумаги / инвестиции',                 19, 2);
insert into genres (id, name, parent_id, level) values (45, 'Бухучет / налогообложение / аудит',          19, 2);
insert into genres (id, name, parent_id, level) values (46, 'Бухгалтерский учет в коммерческих банках',   45, 3);
insert into genres (id, name, parent_id, level) values (47, 'Бухгалтерский учет на предприятиях',         45, 3);
insert into genres (id, name, parent_id, level) values (48, 'Налоги',                                     45, 3);
insert into genres (id, name, parent_id, level) values (49, 'Аудит',                                      45, 3);
insert into genres (id, name, parent_id, level) values (50, 'Российская практика',                        19, 2);
insert into genres (id, name, parent_id, level) values (51, 'Истории успеха',                             19, 2);
insert into genres (id, name, parent_id, level) values (52, 'Интернет-маркетинг',                         19, 2);
insert into genres (id, name, parent_id, level) values (53, 'Лидерство',                                  19, 2);
insert into genres (id, name, parent_id, level) values (54, 'Управление бизнесом',                        19, 2);
insert into genres (id, name, parent_id, level) values (55, 'Драматургия',                                 0, 1);
insert into genres (id, name, parent_id, level) values (56, 'Античная драма',                             55, 2);
insert into genres (id, name, parent_id, level) values (57, 'Комедия',                                    55, 2);
insert into genres (id, name, parent_id, level) values (58, 'Российские коммедии',                        57, 3);
insert into genres (id, name, parent_id, level) values (59, 'Иностранные коммедии',                       57, 3);
insert into genres (id, name, parent_id, level) values (60, 'Сценарий',                                   55, 2);
insert into genres (id, name, parent_id, level) values (61, 'Драма, пьеса',                               55, 2);