package com.lemekh.order.adapter;

import com.lemekh.order.dto.Context;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
public class OrderCompleteAdapter implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        final Context context = (Context) execution.getVariable("order");
        final String traceId = execution.getProcessBusinessKey();

        System.out.println("ORDER COMPLETED! " + context.getTraceId());
    }
}
