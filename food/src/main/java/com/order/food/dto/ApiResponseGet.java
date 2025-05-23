package com.order.food.dto;

import lombok.Data;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Data
public class ApiResponseGet<T> {
    private int status;
    private long totalRecords;
    private boolean hasMore;
    private T data;

    public ApiResponseGet(long totalRecords, boolean hasMore, T data) {
        this.status = (totalRecords==0)?2:1;
        this.totalRecords = totalRecords;
        this.hasMore = hasMore;
        this.data = data;
    }
}
