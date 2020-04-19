package com.lemekh.order.dto;

import com.lemekh.order.enums.OrderStateEnum;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderDTO {
    private List<String> goods;
    private String note;
    private BigDecimal price;
    private OrderStateEnum stateEnum;
}
