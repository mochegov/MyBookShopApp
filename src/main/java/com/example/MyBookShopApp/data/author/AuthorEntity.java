package com.example.MyBookShopApp.data.author;

import com.example.MyBookShopApp.data.book.links.Book2AuthorEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "author")
public class AuthorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "VARCHAR(255)")
    private String photo;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String slug;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @JsonIgnore
    public String getPartDecription(Integer numPart){
        String result;
        if (numPart == 1) {
            result = description.length() <= 1000 ? "[1] " + description : "[1] " + description.substring(0, 1000);
        } else if (numPart == 2)  {
            result = description.length() <= 1000 ? "[2] " : "[2] " + description.substring(1000);
        } else {
            result = "";
        }
        return result;
    }

    // Связь с таблицей, в которой содержатся связи книг и авторов
    @OneToMany(mappedBy = "author")
    @JsonIgnore
    private Set<Book2AuthorEntity> book2AuthorEntities;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Book2AuthorEntity> getBook2AuthorEntities() {
        return book2AuthorEntities;
    }

    public void setBook2AuthorEntities(Set<Book2AuthorEntity> book2AuthorEntities) {
        this.book2AuthorEntities = book2AuthorEntities;
    }
}
