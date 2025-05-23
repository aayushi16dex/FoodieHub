package com.order.food.service;

import com.order.food.dto.OrderRequestDto;
import com.order.food.dto.OrderResponseDto;
import com.order.food.model.Order;

import java.util.List;

public interface OrderService {
    OrderResponseDto createOrder(OrderRequestDto dto);
    List<Order> getAllOrders();
}
