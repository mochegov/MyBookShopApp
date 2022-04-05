package com.example.MyBookShopApp.data.services;

import com.example.MyBookShopApp.data.book.review.BookReviewEntity;
import com.example.MyBookShopApp.data.book.review.BookReviewLikeEntity;
import com.example.MyBookShopApp.data.user.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookReviewLikeService {
    private BookReviewLikeRepository bookReviewLikeRepository;

    @Autowired
    public BookReviewLikeService(BookReviewLikeRepository bookReviewLikeRepository) {
        this.bookReviewLikeRepository = bookReviewLikeRepository;
    }

    public List<BookReviewLikeEntity> getBookReviewLikeByReviewId(BookReviewEntity bookReview){
        return bookReviewLikeRepository.findBookReviewLikeEntitiesByReviewId(bookReview.getId());
    }

    public void addNewBookReviewLike(BookReviewEntity bookReview, UserEntity user, Short value) {
        BookReviewLikeEntity bookReviewLike = new BookReviewLikeEntity();
        bookReviewLike.setReviewId(bookReview.getId());
        bookReviewLike.setUser(user);
        bookReviewLike.setTime(LocalDateTime.now());
        bookReviewLike.setValue(value);
        bookReviewLikeRepository.saveAndFlush(bookReviewLike);
    }
}
