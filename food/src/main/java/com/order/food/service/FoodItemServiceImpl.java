package com.order.food.service;

import com.order.food.dto.FoodItemDto;
import com.order.food.enums.FoodType;
import com.order.food.exception.IdNotFoundException;
import com.order.food.exception.InvalidValueException;
import com.order.food.exception.RecordNotFoundException;
import com.order.food.model.FoodCategory;
import com.order.food.model.FoodItem;
import com.order.food.repository.FoodCategoryRepository;
import com.order.food.repository.FoodItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Service
public class FoodItemServiceImpl implements FoodItemService {

    @Autowired
    private FoodItemRepository foodItemRepository;

    @Autowired
    private FoodCategoryRepository foodCategoryRepository;

    @Override
    public Page<FoodItem> getItems(int pageNo, int pageSize, String foodType, int foodCategoryId, Boolean isSignatureFood) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        if (foodType != null) {
            FoodType foodTypeVal = FoodType.valueOf(foodType);
            return foodItemRepository.findByFoodTypeAndFoodCategory_Id(foodTypeVal, foodCategoryId, pageable);
        } else if (isSignatureFood != null)
            return foodItemRepository.findBySignatureFood(isSignatureFood, pageable);
        else
            return foodItemRepository.findByFoodCategory_Id(foodCategoryId, pageable);
    }

    public int addorUpdateFoodItem(FoodItemDto dto, Long id) {
        Optional<FoodCategory> categoryType = foodCategoryRepository.findById(dto.getFoodCategoryId());
        FoodCategory foodCategory;
        if (categoryType.isEmpty()) {
            throw new IdNotFoundException("Invalid foodCategory id: " + dto.getFoodCategoryId());
        } else {
            foodCategory = categoryType.get();
        }
        FoodItem food = new FoodItem();

        // Update case
        if (id != null) {
            Optional<FoodItem> foodItem = foodItemRepository.findById(id);
            if (foodItem.isEmpty())
                throw new RecordNotFoundException("No item found with id " + id);
            else
                food.setId(id);
        }
        food.setFoodName(dto.getFoodName());
        food.setFoodDescription(dto.getFoodDescription());
        food.setPrice(dto.getPrice());
        food.setImageUrl(dto.getImageUrl());
        food.setFoodCategory(foodCategory);

        boolean isValid = false;
        for (FoodType type : FoodType.values()) {
            if (type.name().equalsIgnoreCase(dto.getFoodType())) {
                food.setFoodType(type);
                isValid = true;
                break;
            }
        }
        if (!isValid) {
            throw new InvalidValueException("Invalid food type: " + dto.getFoodType()
                    + ". Valid food types: " + Arrays.toString(FoodType.values()));
        }
        if (dto.getSignatureFood() != null)
            food.setSignatureFood(dto.getSignatureFood());
        else
            food.setSignatureFood(false);
        foodItemRepository.save(food);
        return 1;

    }


    public int deleteFoodItem(long id) {
        Optional<FoodItem> item = foodItemRepository.findById(id);
        if (item.isPresent()){
            foodItemRepository.deleteById(id);
            return 1;
        }
        return 2;
    }
}
