package com.order.food.dto;
import lombok.Data;

@Data
public class ApiResponse {
    int status;
    String message;

    public ApiResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
