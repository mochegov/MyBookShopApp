package com.example.MyBookShopApp.data.services;

import com.example.MyBookShopApp.data.book.BookEntity;
import com.example.MyBookShopApp.data.book.links.Book2UserTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<BookEntity, Integer> {
    // Не получилось сделать функцию через название, приходится напрямую через SQL-запрос...
    @Query(value = "select b.* from book b where b.pub_date > TO_DATE('01.01.2020', 'dd.mm.yyyy')", nativeQuery = true)
    List<BookEntity> findBookEntityWithDateOfPublicAfter2020();

    // Функция поиска через сумму книги: выбрать книги, стоимость которых превышает заданное значение
    List<BookEntity> findByPriceAfter(Integer price);

    // Если книги упоминаются в таблице book2user, будем считать, что они популярные
    @Query(value = "select b.* from book b where exists (select 1 from book2user b2u where b2u.book_id = b.id)", nativeQuery = true)
    List<BookEntity> findPopularBook();

    // Поиск книг пользователя по ID типа связи и ID пользователя
    @Query(value = "select b.* from book b where exists " +
                  "(select 1 from book2user b2u where b2u.book_id = b.id and b2u.type_id = :b2utId and b2u.user_id = :userId)", nativeQuery = true)
    List<BookEntity> findBooksByUser(@Param("b2utId") Integer b2utId, @Param("userId") Integer userId);
}