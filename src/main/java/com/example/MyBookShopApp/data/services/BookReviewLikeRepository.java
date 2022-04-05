package com.example.MyBookShopApp.data.services;

import com.example.MyBookShopApp.data.book.review.BookReviewLikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookReviewLikeRepository extends JpaRepository<BookReviewLikeEntity, Integer> {
    List<BookReviewLikeEntity> findBookReviewLikeEntitiesByReviewId(Integer reviewId);
}
