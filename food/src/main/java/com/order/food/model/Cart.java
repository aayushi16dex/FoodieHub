package com.order.food.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "customer_id_fk")
    private Customer customer;
    private long customerId;
    @ManyToOne
    private FoodItem foodItem;
    private int quantity;
}
