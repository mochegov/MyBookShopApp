package com.example.MyBookShopApp.data;

import javax.persistence.*;
import java.util.Set;

// Отложенные книги пользователей
@Entity
@Table(name = "postoned_books")
public class PostonedBooks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Пользователь - внешний ключ
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Книги, отложенные пользователем
    @ManyToMany(mappedBy = "postonedBooks")
    private Set<Book> books;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }
}
