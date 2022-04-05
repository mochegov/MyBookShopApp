package com.example.MyBookShopApp.data.services;

import com.example.MyBookShopApp.data.book.BookEntity;
import com.example.MyBookShopApp.data.book.review.BookRatingsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BookRatingsService {
    private BookRatingsRepository bookRatingsRepository;

    @Autowired
    public BookRatingsService(BookRatingsRepository bookRatingsRepository) {
        this.bookRatingsRepository = bookRatingsRepository;
    }

    // Добавить рейтинг книге
    public void addRatingForBook(BookEntity book, Short rating){
        BookRatingsEntity bookRating = new BookRatingsEntity();
        bookRating.setBook(book);
        bookRating.setRating(rating);
        bookRating.setTime(LocalDateTime.now());
        bookRatingsRepository.saveAndFlush(bookRating);
    }

    // Получение рейтингов книги
    public List<Integer> getRatingsOfBook(BookEntity book){
        List<Integer> resultRatings = new ArrayList<>();
        List<BookRatingsEntity> bookRatings = bookRatingsRepository.getAllByBookId(book.getId());

        // Получение суммарных рейтингов каждой книги
        Map<Short, Long> ratingsMap = bookRatings.stream().collect(Collectors.groupingBy(bookRatingsEntity -> bookRatingsEntity.getRating(), Collectors.counting()));

        // Заполнение результирующей таблицы рейтингов: 1: a, 2: b, 3: c, 4: d, 5: e
        for (short i = 1; i <= 5; i++) {
            Long ratingValue = 0L;
            if (ratingsMap.containsKey(Short.valueOf(i))) {
                ratingValue = ratingsMap.get(Short.valueOf(i));
            }
            resultRatings.add(Integer.valueOf(ratingValue.intValue()));
        }

        return resultRatings;
    }

    // Получение суммарного рейтинга книги
    public Integer getTotalRatingOfBook(List<Integer> resultRatings){
        Integer totalRating = 0;
        Integer i = 1;
        for (Integer rating: resultRatings) {
            totalRating = totalRating + i * rating;
            i = i + 1;
        }

        return totalRating;
    }

    // Получение среднего рейтинга книги
    public Integer getAvgRatingOfBook(List<Integer> resultRatings){
        double totalRating = 0D;
        double countOfRatings = 0D;
        int i = 1;
        for (Integer rating: resultRatings) {
            totalRating = totalRating + i * rating.intValue();
            countOfRatings = countOfRatings + rating;
            i = i + 1;
        }

        long avgRatingRnd = Math.round(totalRating / countOfRatings);

        return countOfRatings != 0 ? Integer.valueOf(Long.valueOf(avgRatingRnd).intValue()) : 0;
    }

    // Получение результата по рейтингам в виде списка
    public List<Boolean> getResultOfRatings(Integer avgRating){
        List<Boolean> resultRatings = new ArrayList<>();
        for (Integer i = 1; i <= 5; i++) {
            resultRatings.add(avgRating >= i ? true : false);
        }
        return resultRatings;
    }
}
