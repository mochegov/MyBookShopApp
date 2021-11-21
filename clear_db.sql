-- Очистка таблиц, ссылающихся на данные других таблиц
delete from public.book2author;
delete from public.book2genre;
delete from public.book2user;
delete from public.book_file;
delete from public.book_review;
delete from public.book_review_like;
delete from public.user_contact;
delete from public.message;
delete from public.balance_transaction;
delete from public.file_download;

-- Очистка основных таблиц
delete from public.author;
delete from public.book;
delete from public.genre;
delete from public.users;

-- Очистка справочников
delete from public.book_file_type;
delete from public.book2user_type;
delete from public.document;
delete from public.faq;

-- Удаление таблиц, ссылающихся на данные других таблиц
drop table public.book2author;
drop table public.book2genre;
drop table public.book2user;
drop table public.book_file;
drop table public.book_review;
drop table public.book_review_like;
drop table public.user_contact;
drop table public.message;
drop table public.balance_transaction;
drop table public.file_download;

-- Удаление основных таблиц
drop table public.author;
drop table public.book;
drop table public.genre;
drop table public.users;

-- Удаление справочников
drop table public.book_file_type;
drop table public.book2user_type;
drop table public.document;
drop table public.faq;

-- Удаление последовательностей
drop sequence public.author_id_seq;
drop sequence public.balance_transaction_id_seq;
drop sequence public.book2author_id_seq;
drop sequence public.book2genre_id_seq;
drop sequence public.book2user_id_seq;
drop sequence public.book2user_type_id_seq;
drop sequence public.book_file_id_seq;
drop sequence public.book_file_type_id_seq;
drop sequence public.book_id_seq;
drop sequence public.book_review_id_seq;
drop sequence public.book_review_like_id_seq;
drop sequence public.document_id_seq;
drop sequence public.faq_id_seq;
drop sequence public.file_download_id_seq;
drop sequence public.genre_id_seq;
drop sequence public.message_id_seq;
drop sequence public.user_contact_id_seq;
drop sequence public.users_id_seq;