package com.order.food.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = false)
public class OrderItemResponseDto {
    private String foodName;
    private int quantity;
    private double pricePerUnit;
    private double totalPrice;
}
