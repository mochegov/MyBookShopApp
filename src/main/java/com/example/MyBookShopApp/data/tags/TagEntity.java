package com.example.MyBookShopApp.data.tags;

import javax.persistence.*;

@Entity
@Table(name = "tags")
public class TagEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String name;

    @Column(columnDefinition = "INT NOT NULL")
    private Integer amount;

    // Получение класса для HTML-разметки по рейтингу
    public String getClassByAmount(){
        String classHTML;
        switch (amount) {
            case  (5):
                classHTML = "Tag Tag_lg";
                break;
            case (4):
                classHTML = "Tag Tag_md";
                break;
            case (3):
                classHTML = "Tag";
                break;
            case (2):
                classHTML = "Tag Tag_sx";
                break;
            default:
                classHTML = "Tag Tag_sm";
                break;
        }

        return classHTML;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}

