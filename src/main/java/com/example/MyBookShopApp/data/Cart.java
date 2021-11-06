package com.example.MyBookShopApp.data;

import javax.persistence.*;
import java.util.Set;

// Корзины пользователей сайта
@Entity
@Table(name = "carts")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToMany(mappedBy = "carts")
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

    public Set<Book> getBookSet() {
        return books;
    }

    public void setBookSet(Set<Book> books) {
        this.books = books;
    }
}
