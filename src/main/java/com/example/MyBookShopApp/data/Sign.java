package com.example.MyBookShopApp.data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "signs")
public class Sign {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String sign_name;

    @ManyToMany(mappedBy = "signSet")
    private Set<Book> bookSet;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSign_name() {
        return sign_name;
    }

    public void setSign_name(String sign_name) {
        this.sign_name = sign_name;
    }
}