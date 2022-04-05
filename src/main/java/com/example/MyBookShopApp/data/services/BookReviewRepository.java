package com.example.MyBookShopApp.data.services;

import com.example.MyBookShopApp.data.book.review.BookReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookReviewRepository extends JpaRepository<BookReviewEntity, Integer> {
    List<BookReviewEntity> findBookReviewEntityByBook_Id(Integer bookId);
}
