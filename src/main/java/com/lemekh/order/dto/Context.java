package com.lemekh.order.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class Context {
    private String traceId;
    private String type;
    private String id;
    private OrderDTO data;
    private LocalDateTime dateTime;

    public Context(String type, OrderDTO data) {
        this.type = type;
        this.data = data;
        traceId = UUID.randomUUID().toString();
        id = UUID.randomUUID().toString();
        dateTime = LocalDateTime.now();
    }

    public Context() {
    }
}
