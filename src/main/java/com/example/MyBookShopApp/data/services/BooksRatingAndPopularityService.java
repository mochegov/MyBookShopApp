package com.example.MyBookShopApp.data.services;

import com.example.MyBookShopApp.data.book.BookEntity;

import java.math.BigDecimal;

public interface BooksRatingAndPopularityService {
    // Получает текущий рейтинг книги
    BigDecimal getCurrentRating(BookEntity book);

    // Обновляет рейтинг у книги на переданное значение
    void updateRating(BookEntity book, BigDecimal rating, boolean flash);

    // Обновляет рейтинг у всех книг, устанавливает текущий рейтинг
    void updateRatingAllBooks();
}
