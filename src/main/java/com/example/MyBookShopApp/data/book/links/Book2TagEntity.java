package com.example.MyBookShopApp.data.book.links;

import com.example.MyBookShopApp.data.book.BookEntity;
import com.example.MyBookShopApp.data.tags.TagEntity;

import javax.persistence.*;

@Entity
@Table(name = "book2entity")
public class Book2TagEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "book_id", columnDefinition = "INT NOT NULL")
    private BookEntity book;

    @ManyToOne
    @JoinColumn(name = "tag_id", columnDefinition = "INT NOT NULL")
    private TagEntity tag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BookEntity getBook() {
        return book;
    }

    public void setBook(BookEntity book) {
        this.book = book;
    }

    public TagEntity getTag() {
        return tag;
    }

    public void setTag(TagEntity tag) {
        this.tag = tag;
    }
}
