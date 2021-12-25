package com.example.MyBookShopApp.data.user;

import com.example.MyBookShopApp.data.book.file.FileDownloadEntity;
import com.example.MyBookShopApp.data.book.links.Book2AuthorEntity;
import com.example.MyBookShopApp.data.book.links.Book2UserEntity;
import com.example.MyBookShopApp.data.book.review.BookReviewEntity;
import com.example.MyBookShopApp.data.book.review.BookReviewLikeEntity;
import com.example.MyBookShopApp.data.book.review.MessageEntity;
import com.example.MyBookShopApp.data.payments.BalanceTransactionEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String name;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String hash;

    @Column(columnDefinition = "TIMESTAMP NOT NULL")
    private LocalDateTime regTime;

    @Column(columnDefinition = "INT NOT NULL")
    private Integer balance;

    // Связь с таблицей, в которой находится информация о загрузке книги
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Set<FileDownloadEntity> fileDownloadEntities;

    // Связь с таблицей, в которой находится информация о транзакциях пользователя
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Set<BalanceTransactionEntity> balanceTransactionEntities;

    // Связь с таблицей, в которой находятся отзовы о книгах, сделанных пользователями
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Set<BookReviewEntity> bookReviewEntities;

    // Связь с таблицей, в которой находятся сообщения, сделанные зарегистрированными и не зарегистрированными пользователями
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Set<MessageEntity> messageEntities;

    // Связь с таблицей, в которой находятся likes and unlikes, сделанные пользователями
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Set<BookReviewLikeEntity> bookReviewLikeEntities;

    // Связь с таблицей, в которой находится связь между книгами и пользователями
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Set<Book2UserEntity> book2UserEntities;

    // Связь с таблицей, в которой находятся контракты пользователей
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Set<UserContactEntity> userContactEntities;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public LocalDateTime getRegTime() {
        return regTime;
    }

    public void setRegTime(LocalDateTime regTime) {
        this.regTime = regTime;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public Set<FileDownloadEntity> getFileDownloadEntities() {
        return fileDownloadEntities;
    }

    public void setFileDownloadEntities(Set<FileDownloadEntity> fileDownloadEntities) {
        this.fileDownloadEntities = fileDownloadEntities;
    }

    public Set<BalanceTransactionEntity> getBalanceTransactionEntities() {
        return balanceTransactionEntities;
    }

    public void setBalanceTransactionEntities(Set<BalanceTransactionEntity> balanceTransactionEntities) {
        this.balanceTransactionEntities = balanceTransactionEntities;
    }

    public Set<BookReviewEntity> getBookReviewEntities() {
        return bookReviewEntities;
    }

    public void setBookReviewEntities(Set<BookReviewEntity> bookReviewEntities) {
        this.bookReviewEntities = bookReviewEntities;
    }

    public Set<MessageEntity> getMessageEntities() {
        return messageEntities;
    }

    public void setMessageEntities(Set<MessageEntity> messageEntities) {
        this.messageEntities = messageEntities;
    }

    public Set<BookReviewLikeEntity> getBookReviewLikeEntities() {
        return bookReviewLikeEntities;
    }

    public void setBookReviewLikeEntities(Set<BookReviewLikeEntity> bookReviewLikeEntities) {
        this.bookReviewLikeEntities = bookReviewLikeEntities;
    }

    public Set<Book2UserEntity> getBook2UserEntities() {
        return book2UserEntities;
    }

    public void setBook2UserEntities(Set<Book2UserEntity> book2UserEntities) {
        this.book2UserEntities = book2UserEntities;
    }

    public Set<UserContactEntity> getUserContactEntities() {
        return userContactEntities;
    }

    public void setUserContactEntities(Set<UserContactEntity> userContactEntities) {
        this.userContactEntities = userContactEntities;
    }
}
