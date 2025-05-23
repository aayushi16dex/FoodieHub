package com.order.food.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
public class FoodCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
}
