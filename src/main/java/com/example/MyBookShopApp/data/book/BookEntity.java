package com.example.MyBookShopApp.data.book;

import com.example.MyBookShopApp.data.book.file.FileDownloadEntity;
import com.example.MyBookShopApp.data.book.links.Book2AuthorEntity;
import com.example.MyBookShopApp.data.book.links.Book2GenreEntity;
import com.example.MyBookShopApp.data.book.links.Book2UserEntity;
import com.example.MyBookShopApp.data.book.review.BookReviewEntity;
import com.example.MyBookShopApp.data.payments.BalanceTransactionEntity;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Set;

@Entity
@Table(name = "book")
public class BookEntity {

/*
    Пример текущей реализации
      "id": 22,
      "slug": "book22",
      "title": "Обитатели холмов",
      "image": "https://cdn.eksmo.ru/v2/ITD000000000179271/COVER/cover1__w600.jpg",
      "description": null,
      "price": 400,
      "discount": 0,
      "discountPrice": 400,
      "authors": "Ричард Адамс",
      "pubDate": "03.04.2021",
      "isBestseller": false

    Пример образца
      "id": 1,
      "slug": 759144,
      "title": "Джой Уильямс: Подменыш",
      "image": "/assets/img/content/main/card.jpg",
      "authors": "Уильямс Джой",
      "discount": 18,
      "isBestseller": true,
      "rating": 5,
      "status": "KEPT",
      "price": 624,
      "discountPrice": 512
*/

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "pub_date", columnDefinition = "DATE NOT NULL")
    @JsonIgnore
    private Calendar pubDate;

    @Column(name = "is_bestseller", columnDefinition = "INT NOT NULL")
    @JsonIgnore
    private Integer isBestseller;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String slug;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String title;

    @Column(columnDefinition = "VARCHAR(255)")
    private String image;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "INT NOT NULL")
    private Integer price;

    @Column(columnDefinition = "INT NOT NULL DEFAULT 0")
    private Integer discount;

    @Column(columnDefinition = "NUMERIC NOT NULL DEFAULT 0")
    private BigDecimal rating;

    // Связь с таблицей, в которой содержатся связи книг и авторов
    @OneToMany(mappedBy = "book")
    @JsonIgnore
    private Set<Book2AuthorEntity> book2AuthorEntities;

    // Связь с таблицей, в которой содержатся связи книг и жанров
    @OneToMany(mappedBy = "book")
    @JsonIgnore
    private Set<Book2GenreEntity> book2GenreEntities;

    // Связь с таблицей, в которой находится информация о транзакциях пользователя
    @OneToMany(mappedBy = "book")
    @JsonIgnore
    private Set<BalanceTransactionEntity> balanceTransactionEntities;

    // Связь с таблицей, в которой находятся отзовы о книгах, сделанных пользователями
    @OneToMany(mappedBy = "book")
    @JsonIgnore
    private Set<BookReviewEntity> bookReviewEntities;

    // Связь с таблицей, в которой находится связь между книгами и пользователями
    @OneToMany(mappedBy = "book")
    @JsonIgnore
    private Set<Book2UserEntity> book2UserEntities;

    // Связь с таблицей, в которой находится информация о загрузке книги
    @OneToMany(mappedBy = "book")
    @JsonIgnore
    private Set<FileDownloadEntity> fileDownloadEntities;

    // Получить имя автора книги
    @JsonGetter("authors")
    public String getAuthorName(){
        String authorName = "";
        for (Book2AuthorEntity book2Author : this.getBook2AuthorEntities()) {
            authorName = book2Author.getAuthor().getName();
            break;
        }

        return authorName;
    }

    @JsonGetter("isBestseller")
    public boolean itIsBestseller() {
        boolean result = false;
        if (this.isBestseller.intValue() == 1){
            result = true;
        }
        return result;
    }

    // Книга является бестселлером?
    @JsonIgnore
    public boolean isBestSeller(){
        boolean result = false;
        if (this.getIsBestseller().intValue() == 1) {
            result = true;
        }
        return result;
    }

    // Получить старую стоимость книги. Если есть скидка, то мы можем вычислить старую стоимость
    @JsonGetter("discountPrice")
    public Integer getOldPrice(){
        Integer oldPrice;
        if (this.getDiscount() == 0) {
            // Скидки нет, считается что старая стоимость книги равна текущей
            oldPrice = this.getPrice();
        } else {
            oldPrice = this.getPrice() * (this.getDiscount() + 100) / 100;
        }
        return oldPrice;
    }

    // Получает строку с датой публикации
    @JsonGetter("pubDate")
    public String getPubDateString(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        return simpleDateFormat.format(pubDate.getTime());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Calendar getPubDate() {
        return pubDate;
    }

    public void setPubDate(Calendar pubDate) {
        this.pubDate = pubDate;
    }

    public Integer getIsBestseller() {
        return isBestseller;
    }

    public void setIs_bestseller(Integer isBestseller) {
        this.isBestseller = isBestseller;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Set<Book2AuthorEntity> getBook2AuthorEntities() {
        return book2AuthorEntities;
    }

    public void setBook2AuthorEntities(Set<Book2AuthorEntity> book2AuthorEntities) {
        this.book2AuthorEntities = book2AuthorEntities;
    }

    public Set<Book2GenreEntity> getBook2GenreEntities() {
        return book2GenreEntities;
    }

    public void setBook2GenreEntities(Set<Book2GenreEntity> book2GenreEntities) {
        this.book2GenreEntities = book2GenreEntities;
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

    public Set<Book2UserEntity> getBook2UserEntities() {
        return book2UserEntities;
    }

    public void setBook2UserEntities(Set<Book2UserEntity> book2UserEntities) {
        this.book2UserEntities = book2UserEntities;
    }

    public Set<FileDownloadEntity> getFileDownloadEntities() {
        return fileDownloadEntities;
    }

    public void setFileDownloadEntities(Set<FileDownloadEntity> fileDownloadEntities) {
        this.fileDownloadEntities = fileDownloadEntities;
    }

    public BigDecimal getRating() {
        return rating;
    }

    public void setRating(BigDecimal rating) {
        this.rating = rating;
    }
}
