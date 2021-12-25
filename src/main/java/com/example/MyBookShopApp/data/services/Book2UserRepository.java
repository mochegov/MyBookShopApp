package com.example.MyBookShopApp.data.services;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.MyBookShopApp.data.book.links.Book2UserEntity;

public interface Book2UserRepository extends JpaRepository<Book2UserEntity, Integer> {
    // Получить количество записей в справочнике по ID книги и коду типа связи "книга-пользователь"
    Integer countAllByBook_IdAndBook2UserType_Code(Integer bookId, String userTypeCode);
}
