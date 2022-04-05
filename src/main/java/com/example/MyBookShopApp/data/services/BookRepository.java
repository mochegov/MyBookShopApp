package com.example.MyBookShopApp.data.services;

import com.example.MyBookShopApp.data.book.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Calendar;
import java.util.List;

public interface BookRepository extends JpaRepository<BookEntity, Integer> {

    // Постраничный поиск книг с заданными датами публикации
    @Query(value = "select b.* from book b where b.pub_date between :beginDate and :endDate order by b.pub_date", nativeQuery = true)
    Page<BookEntity> findBookByPubDate(Calendar beginDate, Calendar endDate, Pageable nextPage);

    // Постраничный поиск книг, у которых задан рейтинг. Выборка упорядочена по убыванию рейтинга.
    Page<BookEntity> findAllByRatingIsNotNullOrderByRatingDesc(Pageable nextPage);

    // Поиск книг пользователя по ID типа связи и ID пользователя
    @Query(value = "select b.* from book b where exists " +
                  "(select 1 from book2user b2u where b2u.book_id = b.id and b2u.type_id = :b2utId and b2u.user_id = :userId)", nativeQuery = true)
    List<BookEntity> findBooksByUser(@Param("b2utId") Integer b2utId, @Param("userId") Integer userId);

    // Постраничный поиск книг по названию
    Page<BookEntity> findBookByTitleContaining(String bookTitle, Pageable nextPage);

    // Постраничный поиск книг, относящихся к определенному жанру (без учета дочерних жанров)
    @Query(value = "select b.* from book b where exists (select 1 from book2genre b2g where b2g.book_id = b.id and b2g.genre_id = :genreId)", nativeQuery = true)
    Page<BookEntity> getPageOfBooksByGenreId(@Param("genreId") Integer genreId, Pageable nextPage);

    // Постраничный поиск книг, относящихся к определенному жанру (с учетом дочерних жанров). К сожалению, драйвер выдает ошибку "неизвестная колонка "genres.id" :(
    @Query(value = "with recursive genres as " +
                   "(select g1.id, g1.parent_id from genre g1 where g1.id = :genreId union all " +
                   "select g2.id, g2.parent_id from genre g2, genres where g2.parent_id = genres.id) " +
                   "select b.* from book b where exists (select 1 from book2genre b2g, genres g where b2g.book_id = b.id and b2g.genre_id = g.id)", nativeQuery = true)
    Page<BookEntity> getPageOfBooksByGenreIdWithRecursion(Integer genreId, Pageable nextPage);

    @Query(value = "select b.* from book b where exists (select 1 from book2author b2a where b2a.book_id = b.id and b2a.author_id = :authorId)", nativeQuery = true)
    Page<BookEntity> getPageBooksByAuthorId(@Param("authorId") Integer authorId, Pageable nextPage);

    @Query(value = "select count(*) from book b where exists (select 1 from book2author b2a where b2a.book_id = b.id and b2a.author_id = :authorId)", nativeQuery = true)
    Integer countBookByAuthorId(@Param("authorId") Integer authorId);

    // Постраничный поиск книг, относящихся к определенному тэгу
    @Query(value = "select b.* from book b where exists (select 1 from book2tags b2t where b2t.book_id = b.id and b2t.tags_id = :tagId)", nativeQuery = true)
    Page<BookEntity> getPageOfBooksByTagId(@Param("tagId") Integer tagId, Pageable nextPage);

    // Получение списка книг по массиву их ID
    List<BookEntity> getBookEntitiesByIdIn(List<Integer> listId);
}