package com.order.food.service;

import com.order.food.dto.FoodItemDto;
import com.order.food.model.FoodItem;
import org.springframework.data.domain.Page;

public interface FoodItemService {
    int addorUpdateFoodItem(FoodItemDto dto, Long id);
    Page<FoodItem> getItems(int pageNo, int pageSize);
    int deleteFoodItem(long id);
}
