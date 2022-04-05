package com.example.MyBookShopApp.data.book.links;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "book2user_type")
public class Book2UserTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String code;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String name;

    // Связь с таблицей, в которой находится связь между книгами и пользователями
    @OneToMany(mappedBy = "book2UserType")
    private Set<Book2UserEntity> book2UserEntities;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Book2UserEntity> getBook2UserEntities() {
        return book2UserEntities;
    }

    public void setBook2UserEntities(Set<Book2UserEntity> book2UserEntities) {
        this.book2UserEntities = book2UserEntities;
    }
}
