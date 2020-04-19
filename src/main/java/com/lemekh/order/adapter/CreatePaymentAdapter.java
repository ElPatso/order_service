package com.lemekh.order.adapter;

import com.lemekh.order.dto.Context;
import com.lemekh.order.dto.OrderDTO;
import com.lemekh.order.handler.EventHandler;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreatePaymentAdapter implements JavaDelegate {

    private final EventHandler eventHandler;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        final Context context = (Context) execution.getVariable("order");
        final String traceId = execution.getProcessBusinessKey();
        System.out.println(traceId);
        eventHandler.producer("payment_service", context);
    }
}
