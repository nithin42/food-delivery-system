package com.payment_service.service;

import java.util.List;

import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payment_service.dto.OrderEvent;
import com.payment_service.model.Payment;
import com.payment_service.repository.PaymentRepository;

@Service
public class PaymentService {
	@Autowired
	private PaymentRepository paymentRepository;

    public void processPayment(OrderEvent orderEvent) {
        System.out.println("Processing payment for order: " + orderEvent.getOrderId());

        // Simulated logic
        if ("PLACED".equals(orderEvent.getStatus())) {
            System.out.println("Payment Successful for order " + orderEvent.getOrderId());
        }
    }
    
    
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public Payment getPaymentByOrderId(Long orderId) {
        return paymentRepository.findByOrderId(orderId)
            .orElseThrow(() -> new ResourceNotFoundException("No payment found for orderId: " + orderId));
    }
}

