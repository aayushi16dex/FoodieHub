package com.order.food.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Data
public class ErrorResponse {
    private int status;
    private String message;
    private List<String> errorDetails;
    private String timestamp;
    private HttpStatus statusCode;

    public ErrorResponse(String message, List<String> errorDetails, HttpStatus statusCode) {
        this.status = 2;
        this.message = message.toUpperCase();
        this.errorDetails = errorDetails;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        this.timestamp = LocalDateTime.now().format(formatter);
        this.statusCode = statusCode;
    }
}
