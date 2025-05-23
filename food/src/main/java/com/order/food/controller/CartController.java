package com.order.food.controller;

import com.order.food.dto.*;
import com.order.food.service.CartService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/add-to-cart")
    public ResponseEntity<?> addToCart(@Valid @RequestBody CartRequestDto dto){
         cartService.addToCart(dto);
         String message = "Added to cart successfully";
        return new ResponseEntity<>(new ApiResponse(1,message), HttpStatus.CREATED);
    }

    @PutMapping("/reduce-cart-item")
    public ResponseEntity<?> reduceCartItem(@Valid @RequestBody CartRequestDto dto){
        int status = cartService.reduceCartItemQuantity(dto);
        String message = (status==1)?"Success":"Item not found in the cart";
        return new ResponseEntity<>(new ApiResponse(status,message), HttpStatus.OK);
    }

    @DeleteMapping("/delete-cart-item")
    public ResponseEntity<?> deleteCartItem(@RequestBody CartRequestDto dto){
        int status = cartService.deleteItemFromCart(dto);
        String message = (status==1)?"Success":"Item not found in the cart";
        return new ResponseEntity<>(new ApiResponse(status,message), HttpStatus.OK);
    }

    @DeleteMapping("/customer/{customerId}")
    public ResponseEntity<?> deleteCart(@PathVariable long customerId){
        int status = cartService.deleteCart(customerId);
        String message = (status==1)?"Success":"Item not found in the cart";
        return new ResponseEntity<>(new ApiResponse(status,message), HttpStatus.OK);
    }

    @GetMapping("customer/{customerId}")
    public ResponseEntity<ApiResponseGet<CartResponseDto>> fetchCartItems(@PathVariable long customerId){
        CartResponseDto responseDto = cartService.fetchCartItems(customerId);
        ApiResponseGet<CartResponseDto> apiResponse = new ApiResponseGet<>(
                responseDto.getItems().size(),
                false,
                responseDto
        );
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
