package com.order.food.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.order.food.enums.FoodType;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = false)
public class FoodItemDto {
    private String foodName;
    private String foodDescription;
    private double price;
    private String imageUrl;
    private Long foodCategoryId;
    private FoodType foodType;
}