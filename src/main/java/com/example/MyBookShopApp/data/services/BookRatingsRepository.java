package com.example.MyBookShopApp.data.services;

import com.example.MyBookShopApp.data.book.review.BookRatingsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRatingsRepository extends JpaRepository<BookRatingsEntity, Integer> {
}
