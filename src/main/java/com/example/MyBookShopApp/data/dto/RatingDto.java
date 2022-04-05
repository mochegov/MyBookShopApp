package com.example.MyBookShopApp.data.dto;

public class RatingDto {
    private Integer Rating;
    private Integer Value;

    public RatingDto(Integer rating, Integer value) {
        Rating = rating;
        Value = value;
    }

    public Integer getRating() {
        return Rating;
    }

    public Integer getValue() {
        return Value;
    }
}