package com.order.food.service;

import com.order.food.dto.*;
import com.order.food.model.*;
import com.order.food.repository.CartRepository;
import com.order.food.utils.AppUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService{

    @Autowired
    AppUtils appUtils;

    @Autowired
    CartRepository cartRepository;

    @Override
    public void addToCart(CartRequestDto dto) {
        Customer customer = appUtils.validateCustomer(dto.getCustomerId());
        FoodItem foodItem = appUtils.validateFoodItem(dto.getFoodItemId());

        Optional<Cart> existing = cartRepository.findByCustomerAndFoodItem(customer, foodItem);

        if (existing.isPresent()){
            Cart item = existing.get();
            item.setQuantity(item.getQuantity() + dto.getQuantity());
            cartRepository.save(item);
        }
        else{
            Cart newCartItem = new Cart();
            newCartItem.setQuantity(dto.getQuantity());
            newCartItem.setFoodItem(foodItem);
            newCartItem.setCustomer(customer);
            newCartItem.setCustomerId(customer.getCustomerId());
            cartRepository.save(newCartItem);
        }
    }

    @Override
    public int reduceCartItemQuantity(CartRequestDto dto){
        Customer customer = appUtils.validateCustomer(dto.getCustomerId());
        FoodItem foodItem = appUtils.validateFoodItem(dto.getFoodItemId());

        Optional<Cart> existing = cartRepository.findByCustomerAndFoodItem(customer, foodItem);

        if (existing.isPresent()){
            Cart item = existing.get();
            int newQty = item.getQuantity() - dto.getQuantity();
            if (newQty>0) {
                item.setQuantity(item.getQuantity() - dto.getQuantity());
                cartRepository.save(item);
            }
            else{
                deleteItemFromCart(dto);
            }
            return 1;
        }
        else{
            return 2;
        }
    }

    @Override
    public int deleteItemFromCart(CartRequestDto dto){
        Customer customer = appUtils.validateCustomer(dto.getCustomerId());
        FoodItem foodItem = appUtils.validateFoodItem(dto.getFoodItemId());
        Optional<Cart> existing = cartRepository.findByCustomerAndFoodItem(customer, foodItem);

        if (existing.isPresent()){
            Cart item = existing.get();
            cartRepository.delete(item);
            return 1;
        }
        else{
            return 2;
        }
    }

    @Override
    public int deleteCart(long customerId){
        Customer customer = appUtils.validateCustomer(customerId);
        Optional<List<Cart>> existing = cartRepository.findAllByCustomerId(customerId);

        if (existing.isPresent()){
            List<Cart> cartItems = existing.get();
            cartRepository.deleteAll(cartItems);
            return 1;
        }
        else{
            return 2;
        }
    }

    @Override
    public CartResponseDto fetchCartItems(long customerId) {
        Customer customer = appUtils.validateCustomer(customerId);
        Optional<List<Cart>> existing = cartRepository.findAllByCustomerId(customerId);
        CartResponseDto cartDto = new CartResponseDto();
        List<CartItemResponseDto> cartItemList = new ArrayList<>();
        double totalCartPrice = 0.0;

        if (existing.isPresent()){
            List<Cart> cartList = existing.get();
            for (Cart cartItem : cartList) {
                CartItemResponseDto cartItemDto = new CartItemResponseDto();

                int itemQty = cartItem.getQuantity();
                double pricePerItem = cartItem.getFoodItem().getPrice();
                double totalPrice = itemQty * pricePerItem;

                cartItemDto.setFoodItemId(cartItem.getFoodItem().getId());
                cartItemDto.setFoodItemName(cartItem.getFoodItem().getFoodName());
                cartItemDto.setQuantity(itemQty);
                cartItemDto.setPrice(pricePerItem);
                cartItemDto.setTotalPrice(appUtils.roundToTwoDecimal(totalPrice));

                cartItemList.add(cartItemDto);
                totalCartPrice += totalPrice;
            }
        }

        cartDto.setCustomerId(customerId);
        cartDto.setItems(cartItemList);
        cartDto.setCartTotal(appUtils.roundToTwoDecimal(totalCartPrice));

        return cartDto;
    }
}
