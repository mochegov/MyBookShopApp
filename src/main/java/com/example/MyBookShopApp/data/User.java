package com.example.MyBookShopApp.data;

import javax.persistence.*;
import java.sql.ResultSet;
import java.util.List;
import java.util.Set;

// Зарегистрированные пользователи сайта
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;

    // Корзины пользователей
    @OneToMany(mappedBy = "user")
    private Set<Cart> carts;

    // Отложенные книги пользователей
    @OneToMany(mappedBy = "user")
    private Set<PostonedBooks> postonedBooks;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
