package com.bulkmex.service;

import com.bulkmex.exception.PaymentNotFoundException;
import com.bulkmex.model.*;
import com.bulkmex.model.enums.PaymentMethod;
import com.bulkmex.model.enums.PaymentStatus;
import com.bulkmex.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final OrderService orderService;

    public PaymentService(PaymentRepository paymentRepository, OrderService orderService) {
        this.paymentRepository = paymentRepository;
        this.orderService = orderService;
    }

    public Payment processPayment(Long orderId, PaymentMethod method, String transactionId) {
        Order order = orderService.getOrderById(orderId); // Obtiene la Order desde el ID
        Payment payment = new Payment();
        payment.setOrder(order);
        payment.setAmount(order.getOrderAmount());
        payment.setPaymentMethod(method);
        payment.setTransactionId(transactionId);
        payment.setDate(LocalDateTime.now());
        payment.setStatus(PaymentStatus.COMPLETED);

        return paymentRepository.save(payment);
    }

    public Payment getPaymentByOrder(Long orderId) {
        return paymentRepository.findByOrderId(orderId)
                .orElseThrow(() -> new PaymentNotFoundException(orderId));
    }
}