package com.example.MyBookShopApp.data.services;

import com.example.MyBookShopApp.data.book.BookEntity;
import com.example.MyBookShopApp.data.book.review.BookReviewEntity;
import com.example.MyBookShopApp.data.book.review.BookReviewLikeEntity;
import com.example.MyBookShopApp.data.user.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class BookReviewService {
    private BookReviewRepository bookReviewRepository;
    private BookReviewLikeRepository bookReviewLikeRepository;

    @Autowired
    public BookReviewService(BookReviewRepository bookReviewRepository, BookReviewLikeRepository bookReviewLikeRepository) {
        this.bookReviewRepository = bookReviewRepository;
        this.bookReviewLikeRepository = bookReviewLikeRepository;
    }

    public void addNewReview(BookEntity book, UserEntity user, String text){
        BookReviewEntity bookReview = new BookReviewEntity();
        bookReview.setBook(book);
        bookReview.setTime(LocalDateTime.now());
        bookReview.setUser(user);
        bookReview.setText(text);
        bookReviewRepository.saveAndFlush(bookReview);
    }

    public List<BookReviewEntity> getBookReviewByBook(BookEntity book){
        return bookReviewRepository.findBookReviewEntityByBook_Id(book.getId());
    }

    public BookReviewEntity getBookReviewById(Integer bookReviewId){
        return bookReviewRepository.getOne(bookReviewId);
    }

    public Map<BookReviewEntity, List<BookReviewLikeEntity>> getReviewMapByBook(BookEntity book){
        List<BookReviewEntity> bookReviewList = getBookReviewByBook(book);
        return bookReviewList.stream().collect(Collectors.toMap(Function.identity(),
                bookReview -> bookReviewLikeRepository.findBookReviewLikeEntitiesByReviewId(bookReview.getId())));
    }

    // Результат: карта отзывов, в каждом из которых пара результатов: like и dislike
    public Map<BookReviewEntity, Pair<Long, Long>> getReviewMapWithRatingsByBook(BookEntity book){
        Map<BookReviewEntity, List<BookReviewLikeEntity>> bookReviewListMap = getReviewMapByBook(book);
        Map<BookReviewEntity, Pair<Long, Long>> resultMap = new HashMap<>();

        for (Map.Entry<BookReviewEntity, List<BookReviewLikeEntity>> bookReviewListEntry: bookReviewListMap.entrySet()) {
            Map<Integer, Long> resultForBookReview = bookReviewListEntry.getValue().stream().collect(Collectors.groupingBy(bookReviewLike -> {
                if (bookReviewLike.getValue() > 0) {
                    return 1;
                } else {
                    return -1;
                }
            }, Collectors.counting()));

            Long like = 0L;
            if (resultForBookReview.containsKey(1)){
                like = resultForBookReview.get(1);
            }

            Long dislike = 0L;
            if (resultForBookReview.containsKey(-1)){
                dislike = resultForBookReview.get(-1);
            }

            resultMap.put(bookReviewListEntry.getKey(), Pair.of(like, dislike));
        }

        return resultMap;
    }

    public List<Boolean> getResultReviewRatings(Map<BookReviewEntity, Pair<Long, Long>> bookReviewPairMap) {
        List<Boolean> resultReviewRatings = new ArrayList<>();
        Long like = 0L;
        Long dislike = 0L;

        for (Map.Entry<BookReviewEntity, Pair<Long, Long>> bookReviewPairEntry: bookReviewPairMap.entrySet()) {
            like = like + bookReviewPairEntry.getValue().getFirst();
            dislike = dislike + bookReviewPairEntry.getValue().getSecond();
        }

        if ((like > 0) && (like > dislike)) {
            Long ratingReview = Math.round((like.doubleValue() - dislike.doubleValue()) / like.doubleValue() * 100.0 / 20.0);
            for (int i = 1; i <= 5; i++) {
                if (ratingReview >= i) {
                    resultReviewRatings.add(true);
                } else {
                    resultReviewRatings.add(false);
                }
            }
        } else {
            resultReviewRatings = Arrays.asList(false, false, false, false, false);
        }

        return resultReviewRatings;
    }
}
