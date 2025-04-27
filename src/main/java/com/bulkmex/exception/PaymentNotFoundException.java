package com.bulkmex.exception;

public class PaymentNotFoundException extends RuntimeException {
    public PaymentNotFoundException(Long orderId) {
        super("Payment not found for order: " + orderId);
    }
}
