package com.example.MyBookShopApp.data.genre;

import com.example.MyBookShopApp.data.book.BookEntity;
import com.example.MyBookShopApp.data.book.links.Book2GenreEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Entity
@Table(name = "genre")
public class GenreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "parent_id", columnDefinition = "INT")
    private Integer parentId;

    @Column(columnDefinition = "INT NOT NULL")
    private Integer level;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String slug;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String name;

    // Связь с таблицей, в которой содержатся связи книг и жанров
    @OneToMany(mappedBy = "genre")
    @JsonIgnore
    private Set<Book2GenreEntity> book2GenreEntities;

    // Список дочерних жанров
    @Transient
    private List<GenreEntity> childGenres;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Book2GenreEntity> getBook2GenreEntities() {
        return book2GenreEntities;
    }

    public void setBook2GenreEntities(Set<Book2GenreEntity> book2GenreEntities) {
        this.book2GenreEntities = book2GenreEntities;
    }

    public List<GenreEntity> getChildGenres() {
        return childGenres;
    }

    public void setChildGenres(List<GenreEntity> childGenres) {
        this.childGenres = childGenres;
    }
}
