package com.order.food.controller;

import com.order.food.dto.ApiResponse;
import com.order.food.dto.ApiResponseGet;
import com.order.food.dto.FoodItemDto;
import com.order.food.exception.RecordNotFoundException;
import com.order.food.model.FoodItem;
import com.order.food.service.FoodItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/foodItems")
public class FoodItemController {
    FoodItemService foodItemService;

    @Autowired
    public FoodItemController(FoodItemService foodItemService) {
        this.foodItemService = foodItemService;
    }

    @GetMapping
    public ResponseEntity<ApiResponseGet<List<FoodItem>>> getAllItems(
            @RequestParam(defaultValue = "1") int pageNo,
            @RequestParam(defaultValue = "20") int itemPerPage,
            @RequestParam(required = false) String foodType,
            @RequestParam(required = false) int foodCategoryId,
            @RequestParam(required = false) Boolean signatureFood
    ){
        Page<FoodItem> response = foodItemService.getItems(pageNo, itemPerPage, foodType, foodCategoryId, signatureFood);
        ApiResponseGet<List<FoodItem>> apiResponse = new ApiResponseGet<>(
                response.getTotalElements(),
                !response.isLast(),
                response.getContent()
        );
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> addFood(@RequestBody FoodItemDto foodItem){
        int status = foodItemService.addorUpdateFoodItem(foodItem, null);
        String message = status == 1
                ? String.format("FoodItem added successfully.")
                : String.format("Failed to add FoodItem.");

        return new ResponseEntity<>(new ApiResponse(status, message), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateFood(@RequestBody FoodItemDto foodItem, @PathVariable long id){
        int status = foodItemService.addorUpdateFoodItem(foodItem, id);
        String message = status == 1
                ? String.format("FoodItem updated successfully.")
                : String.format("Failed to update FoodItem.");

        return new ResponseEntity<>(new ApiResponse(status, message), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteFood(@PathVariable long id) {
        int status = foodItemService.deleteFoodItem(id);
        if (status == 1) {
            String message = String.format("Deleted foodItem id %d.", id);
            return ResponseEntity.ok(new ApiResponse(status, message));
        }
        else{
            throw new RecordNotFoundException("Record with id " + id + " does not exist.");
        }
    }
}