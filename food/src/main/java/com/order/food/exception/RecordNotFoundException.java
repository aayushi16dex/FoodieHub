package com.order.food.exception;

public class RecordNotFoundException extends  RuntimeException{
    public RecordNotFoundException(String message) {
        super(message);
    }
}
