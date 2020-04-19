package com.lemekh.order.service.implementation;

import com.lemekh.order.dto.Context;
import com.lemekh.order.dto.OrderDTO;
import com.lemekh.order.enums.OrderStateEnum;
import com.lemekh.order.handler.EventHandler;
import com.lemekh.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final EventHandler eventHandler;

    @Override
    public void createOrder(final Context order) {
        order.getData().setStateEnum(OrderStateEnum.NEW);
        order.setType("CreatePayment");
        eventHandler.producer("order_saga_server", order);
    }
}
