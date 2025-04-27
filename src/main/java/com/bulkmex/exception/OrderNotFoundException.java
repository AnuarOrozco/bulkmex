package com.bulkmex.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND) // Retorna 404 automáticamente
public class OrderNotFoundException extends RuntimeException {

  public OrderNotFoundException(Long orderId) {
    super("Order not found with ID: " + orderId);
  }

  // Opcional: Si necesitas buscar por otros campos
  public OrderNotFoundException(String fieldName, Object fieldValue) {
    super(String.format("Order not found with %s: %s", fieldName, fieldValue));
  }
}