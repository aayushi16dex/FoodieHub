package com.order.food.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties
public class CartItemResponseDto {
    private Long foodItemId;
    private String foodItemName;
    private double price;
    private int quantity;
    private double totalPrice;
}
