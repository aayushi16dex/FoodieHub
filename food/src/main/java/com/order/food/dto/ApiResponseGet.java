package com.order.food.dto;

import lombok.Data;

import java.util.List;

@Data
public class ApiResponseGet {
    private int status;
    private long totalRecords;
    private boolean hasMore;
    private List data;

    public ApiResponseGet(int status, long totalRecords, boolean hasMore, List data) {
        this.status = status;
        this.totalRecords = totalRecords;
        this.hasMore = hasMore;
        this.data = data;
    }
}
