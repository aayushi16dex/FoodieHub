package com.order.food.repository;

import com.order.food.enums.FoodType;
import com.order.food.model.FoodItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodItemRepository extends JpaRepository<FoodItem, Long> {
    Page<FoodItem> findByFoodTypeAndFoodCategory_Id(FoodType foodTypeId, int foodCategoryIds, Pageable pageable);
    Page<FoodItem> findByFoodCategory_Id(int foodCategoryIds, Pageable pageable);
    Page<FoodItem> findBySignatureFood(boolean signatureFood, Pageable pageable);

}
