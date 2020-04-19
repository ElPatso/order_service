package com.lemekh.order.handler;

import com.lemekh.order.dto.Context;
import com.lemekh.order.dto.OrderDTO;
import com.lemekh.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.value.TypedValue;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EventHandler {

    private final KafkaTemplate<String, Context> kafkaTemplate;
    private final ProcessEngine camunda;

    @KafkaListener(groupId = "order", topics = "order_saga_server", containerFactory = "kafkaListenerContainerFactory")
    public void orderSagaServer(@Payload final Context message) {
        OrderDTO order = message.getData();

        System.out.println("New order placed, start flow. " + order);
        TypedValue typedTransientObjectValue = Variables.objectValue(message, true).create();
        // and kick of a new flow instance
        camunda.getRuntimeService().createMessageCorrelation(message.getType())
                .processInstanceBusinessKey(message.getTraceId())
                .setVariable("order", typedTransientObjectValue)
                .correlateWithResult();
    }

    public void producer(final String topic, final Context value) {
        kafkaTemplate.send(topic, value);
    }

}
