package com.order.food.service;

import com.order.food.dto.FoodCategoryGetDto;
import com.order.food.dto.FoodCategoryPostDto;

import java.util.List;

public interface FoodCategoryService {
    int addFoodCategory(FoodCategoryPostDto dto);
    List<FoodCategoryGetDto> fetchFoodCategory();
}
