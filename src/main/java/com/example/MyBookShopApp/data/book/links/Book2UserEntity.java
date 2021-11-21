package com.example.MyBookShopApp.data.book.links;

import com.example.MyBookShopApp.data.book.BookEntity;
import com.example.MyBookShopApp.data.user.UserEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "book2user")
public class Book2UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "TIMESTAMP NOT NULL")
    private LocalDateTime time;

    @ManyToOne
    @JoinColumn(name = "type_id", columnDefinition = "INT NOT NULL")
    private Book2UserTypeEntity book2UserType;

    @ManyToOne
    @JoinColumn(name = "book_id", columnDefinition = "INT NOT NULL")
    private BookEntity book;

    @ManyToOne
    @JoinColumn(name = "user_id", columnDefinition = "INT NOT NULL")
    private UserEntity user;

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

    public Book2UserTypeEntity getBook2UserType() {
        return book2UserType;
    }

    public void setBook2UserType(Book2UserTypeEntity book2UserType) {
        this.book2UserType = book2UserType;
    }

    public BookEntity getBook() {
        return book;
    }

    public void setBook(BookEntity book) {
        this.book = book;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
