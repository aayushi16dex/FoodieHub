package com.order.food.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = false)
public class OrderItemRequestDto {
    private long userId;
    private long foodItemId;
    private int quantity;
}
