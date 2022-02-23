package com.example.MyBookShopApp.data.book.file;

import com.example.MyBookShopApp.data.book.BookEntity;
import com.example.MyBookShopApp.data.enums.BookFileType;

import javax.persistence.*;

@Entity
@Table(name = "book_file")
public class BookFileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String hash;

    @Column(name = "type_id", columnDefinition = "INT NOT NULL")
    private Integer typeId;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String path;

    public String getBookFileExtensionString(){
        return BookFileType.getExtensionStringByTypeId(this.getTypeId());
    }

    @ManyToOne
    @JoinColumn(name = "book_id", columnDefinition = "INT NOT NULL")
    private BookEntity book;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}