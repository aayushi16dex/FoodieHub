package com.order.food.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.order.food.enums.FoodType;
import lombok.Data;
import org.springframework.boot.context.properties.bind.DefaultValue;

@Data
@JsonIgnoreProperties(ignoreUnknown = false)
public class FoodItemDto {
    private String foodName;
    private String foodDescription;
    private double price;
    private String imageUrl;
    private Long foodCategoryId;
    private String foodType;
    private Boolean signatureFood;
}