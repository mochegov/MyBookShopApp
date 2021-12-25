package com.example.MyBookShopApp.data.dto;

import com.example.MyBookShopApp.data.genre.GenreEntity;

public class GenreDto {
    private Integer id;
    private Integer parentId;
    private Integer level;
    private String slug;
    private String name;

    public GenreDto(GenreEntity genre) {
        this.id = genre.getId();
        this.parentId = genre.getParentId();
        this.level = genre.getLevel();
        this.slug = genre.getSlug();
        this.name = genre.getName();
    }

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
}
