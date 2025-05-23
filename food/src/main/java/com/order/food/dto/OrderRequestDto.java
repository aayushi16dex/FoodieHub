package com.order.food.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = false)
public class OrderRequestDto {
    private long customerId;
    private List<OrderItemRequestDto> items;
}
