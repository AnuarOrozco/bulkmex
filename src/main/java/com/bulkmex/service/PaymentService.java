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

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public Payment processPayment(Order order, PaymentMethod method, String transactionId) {
        Payment payment = new Payment();
        payment.setOrder(order);
        payment.setAmount(order.getOrderAmount());
        payment.setPaymentMethod(method);
        payment.setTransactionId(transactionId);
        payment.setDate(LocalDateTime.now());
        payment.setStatus(PaymentStatus.COMPLETED);

        return paymentRepository.save(payment);
    }

    public Payment getPaymentByOrder(Order order) {
        return paymentRepository.findByOrder(order)
                .orElseThrow(() -> new PaymentNotFoundException(order.getId()));
    }
}