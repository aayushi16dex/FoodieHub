package com.order.food.utils;

import com.order.food.exception.RecordNotFoundException;
import com.order.food.model.Customer;
import com.order.food.model.FoodItem;
import com.order.food.repository.CustomerRepository;
import com.order.food.repository.FoodItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class AppUtils {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    FoodItemRepository foodItemRepository;

    public Customer validateCustomer(Long customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new RecordNotFoundException("Customer not found with id " + customerId));
    }

    public FoodItem validateFoodItem(Long foodItemId){
        return foodItemRepository.findById(foodItemId)
                .orElseThrow(() -> new RecordNotFoundException("Food item not found with id: " + foodItemId));
    }

    public double roundToTwoDecimal(double value) {
        return BigDecimal.valueOf(value)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
    }

}
