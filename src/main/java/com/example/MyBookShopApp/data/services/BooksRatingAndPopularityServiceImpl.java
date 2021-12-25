package com.example.MyBookShopApp.data.services;

import com.example.MyBookShopApp.data.book.BookEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Logger;

@Service
public class BooksRatingAndPopularityServiceImpl implements BooksRatingAndPopularityService {
    BookRepository bookRepository;
    Book2UserRepository book2UserRepository;

    @Autowired
    public BooksRatingAndPopularityServiceImpl(BookRepository bookRepository, Book2UserRepository book2UserRepository) {
        this.bookRepository = bookRepository;
        this.book2UserRepository = book2UserRepository;
    }

    @Override
    public BigDecimal getCurrentRating(BookEntity book) {
        // Сколько раз книга была куплена
        Integer countBookPaid = book2UserRepository.countAllByBook_IdAndBook2UserType_Code(book.getId(), "PAID");

        // Сколько раз книга размещена в корзине пользователя
        Integer countBookCart = book2UserRepository.countAllByBook_IdAndBook2UserType_Code(book.getId(), "CART");

        // Сколько раз книга размещена в корзине пользователя
        Integer countBookKept = book2UserRepository.countAllByBook_IdAndBook2UserType_Code(book.getId(), "KEPT");

        // Расчет итогового рейтинга книги по формуле
        BigDecimal rating = BigDecimal.valueOf(countBookPaid + 0.7 * countBookCart + 0.4 * countBookKept);

        Logger.getLogger(BooksRatingAndPopularityServiceImpl.class.getName()).info("getCurrentRating. " +
                "countBookPaid=" + countBookPaid + ", " +
                "countBookCart=" + countBookCart + ", " +
                "countBookKept=" + countBookKept + ", " +
                "rating=" + rating
        );

        return rating;
    }

    @Override
    public void updateRating(BookEntity book, BigDecimal rating, boolean flash) {
        book.setRating(rating);
        if (flash) {
            bookRepository.saveAndFlush(book);
        } else {
            bookRepository.save(book);
        }
    }

    @Override
    public void updateRatingAllBooks() {
        Logger.getLogger(BooksRatingAndPopularityServiceImpl.class.getName()).info("updateRatingAllBooks...");

        List<BookEntity> books = bookRepository.findAll();
        for (BookEntity book : books) {
            updateRating(book, getCurrentRating(book), false);
        }
        bookRepository.flush();
    }
}
