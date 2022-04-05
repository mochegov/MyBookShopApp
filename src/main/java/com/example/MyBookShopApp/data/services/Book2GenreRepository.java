package com.example.MyBookShopApp.data.services;

import com.example.MyBookShopApp.data.book.links.Book2GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Book2GenreRepository  extends JpaRepository<Book2GenreEntity, Integer> {
    // Получить количество записей в справочнике по ID жанра
    Integer countAllByGenre_Id(Integer genreId);
}