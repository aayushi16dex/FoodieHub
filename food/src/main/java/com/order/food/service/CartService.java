package com.order.food.service;

import com.order.food.dto.CartRequestDto;
import com.order.food.dto.CartResponseDto;

public interface CartService {
    void addToCart(CartRequestDto dto);
    CartResponseDto fetchCartItems(long customerId);
    int reduceCartItemQuantity(CartRequestDto dto);
    int deleteItemFromCart(CartRequestDto dto);
    int deleteCart(long customerId);
}
