package com.order.food.enums;

public enum FoodType {
    VEG(1),
    NON_VEG(2);

    private final int value;

    FoodType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static FoodType fromValue(int value) {
        for (FoodType foodType : FoodType.values()) {
            if (foodType.getValue() == value) {
                return foodType;
            }
        }
        throw new IllegalArgumentException("Unexpected value: " + value);
    }
}