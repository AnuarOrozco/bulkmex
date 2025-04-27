package com.bulkmex.controller;

import com.bulkmex.model.Payment;
import com.bulkmex.model.enums.PaymentMethod;
import com.bulkmex.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<Payment> processPayment(
            @RequestParam Long orderId,
            @RequestParam PaymentMethod method,
            @RequestParam String transactionId) {
        return ResponseEntity.ok(
                paymentService.processPayment(orderId, method, transactionId)
        );
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<Payment> getPaymentByOrder(@PathVariable Long orderId) {
        return ResponseEntity.ok(paymentService.getPaymentByOrder(orderId));
    }
}