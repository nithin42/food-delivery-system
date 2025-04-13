package com.payment_service.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.payment_service.dto.OrderEvent;
import com.payment_service.service.PaymentService;

@Component
public class OrderEventListener {

    @Autowired
    private PaymentService paymentService;

    @KafkaListener(topics = "order-events", groupId = "payment-group", containerFactory = "kafkaListenerContainerFactory")
    public void handleOrderEvent(OrderEvent orderEvent) {
        paymentService.processPayment(orderEvent);
    }
}
