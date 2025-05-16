package com.order.food.model;
import com.order.food.enums.FoodType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
public class FoodItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String foodName;
    private String foodDescription;
    private double price;
    private String imageUrl;
    private Long foodCategoryId;
    private FoodType foodType;
}