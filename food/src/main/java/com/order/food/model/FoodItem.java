package com.order.food.model;
import com.order.food.enums.FoodType;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class FoodItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String foodName;
    private String foodDescription;
    private double price;
    private String imageUrl;
    @ManyToOne
    @JoinColumn(name = "food_category_id", referencedColumnName = "id")
    private FoodCategory foodCategory;
    @Enumerated(EnumType.STRING)
    @Column(name = "food_type")
    private FoodType foodType;
    @Column(nullable = false)
    private Boolean signatureFood = false;

}