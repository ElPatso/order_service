package com.lemekh.order.service;

import com.lemekh.order.dto.Context;
import com.lemekh.order.dto.OrderDTO;

public interface OrderService {

    void createOrder(Context order);
}
