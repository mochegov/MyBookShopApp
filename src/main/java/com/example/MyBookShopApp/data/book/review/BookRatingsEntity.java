package com.example.MyBookShopApp.data.book.review;

import com.example.MyBookShopApp.data.book.BookEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "book_ratings")
public class BookRatingsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "TIMESTAMP NOT NULL")
    private LocalDateTime time;

    @ManyToOne
    @JoinColumn(name = "book_id", columnDefinition = "INT NOT NULL")
    private BookEntity book;

    @Column(columnDefinition = "SMALLINT NOT NULL")
    private Short rating;

    public BookRatingsEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public BookEntity getBook() {
        return book;
    }

    public void setBook(BookEntity book) {
        this.book = book;
    }

    public Short getRating() {
        return rating;
    }

    public void setRating(Short rating) {
        this.rating = rating;
    }
}
