package com.example.MyBookShopApp.data.book.file;

import com.example.MyBookShopApp.data.book.BookEntity;
import com.example.MyBookShopApp.data.user.UserEntity;

import javax.persistence.*;

@Entity
@Table(name = "file_download")
public class FileDownloadEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "INT NOT NULL DEFAULT 1")
    private Integer count;

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

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
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
