package com.order.food.repository;

import com.order.food.dto.CartRequestDto;
import com.order.food.model.Cart;
import com.order.food.model.Customer;
import com.order.food.model.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByCustomerAndFoodItem(Customer customer, FoodItem foodItem);
    Optional<List<Cart>> findAllByCustomerId(long customerId);
}
