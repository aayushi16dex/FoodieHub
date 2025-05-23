package com.order.food.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.order.food.enums.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = false)
public class OrderResponseDto {
    private long orderId;
    private String customerName;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;
    private double totalAmount;
    private List<OrderItemResponseDto> orderItems;

}
