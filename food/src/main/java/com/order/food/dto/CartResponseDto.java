package com.order.food.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = false)
public class CartResponseDto {
    private Long customerId;
    private List<CartItemResponseDto> items;
    private double cartTotal;
}
