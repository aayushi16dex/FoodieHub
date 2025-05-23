package com.order.food.controller;

import com.order.food.dto.ApiResponse;
import com.order.food.dto.OrderRequestDto;
import com.order.food.dto.OrderResponseDto;
import com.order.food.service.OrderService;
import com.order.food.service.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponseDto> createOrder(@RequestBody OrderRequestDto dto){
        OrderResponseDto orderDetails = orderService.createOrder(dto);
//        String message = OrderResponseDto == 1
//                ? String.format("Order placed successfully.")
//                : String.format("Failed to place order.");

        return new ResponseEntity<>(orderDetails, HttpStatus.CREATED);
    }

}
