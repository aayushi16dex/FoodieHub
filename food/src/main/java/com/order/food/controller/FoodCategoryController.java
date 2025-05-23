package com.order.food.controller;

import com.order.food.dto.ApiResponse;
import com.order.food.dto.ApiResponseGet;
import com.order.food.dto.FoodCategoryGetDto;
import com.order.food.dto.FoodCategoryPostDto;
import com.order.food.service.FoodCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/foodCategory")
public class FoodCategoryController {
    FoodCategoryService foodCategoryService;

    @Autowired
    public FoodCategoryController(FoodCategoryService foodCategoryService) {
        this.foodCategoryService = foodCategoryService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse> addFoodCategory(@RequestBody FoodCategoryPostDto foodCategory) {
        int status = foodCategoryService.addFoodCategory(foodCategory);
        String message = status == 1
                ? String.format("Food Category added successfully.")
                : String.format("Failed to add Food Category.");

        return new ResponseEntity<>(new ApiResponse(status, message), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ApiResponseGet<List<FoodCategoryGetDto>>> fetchAllFoodCategory() {
        List<FoodCategoryGetDto> foodCategory = foodCategoryService.fetchFoodCategory();
        ApiResponseGet<List<FoodCategoryGetDto>> apiResponse = new ApiResponseGet<>(
                foodCategory.size(),
                false,
                foodCategory
        );
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}