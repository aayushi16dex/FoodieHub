package com.order.food.service;

import com.order.food.dto.FoodItemDto;
import com.order.food.model.FoodItem;
import org.springframework.data.domain.Page;

import java.util.List;

public interface FoodItemService {
    int addorUpdateFoodItem(FoodItemDto dto, Long id);
    Page<FoodItem> getItems(int pageNo, int pageSize, String foodType, int foodCategoryId, Boolean signatureFood);
    int deleteFoodItem(long id);
}
