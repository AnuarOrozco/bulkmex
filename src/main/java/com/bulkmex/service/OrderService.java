package com.bulkmex.service;

import com.bulkmex.exception.OrderNotFoundException;
import com.bulkmex.model.*;
import com.bulkmex.model.enums.OrderStatus;
import com.bulkmex.repository.OrderRepository;
import com.bulkmex.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderService(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public Order createOrder(Order order) {
        // 1. Establece la relación bidireccional con cada item
        order.getItems().forEach(item -> {
            item.setOrder(order); // Asegura la relación
            // Calcula el subtotal si no viene definido
            if (item.getSubtotal() == null && item.getUnitPrice() != null && item.getQuantity() != null) {
                item.setSubtotal(item.getUnitPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
            }
        });

        // 2. Calcula el total
        BigDecimal total = order.getItems().stream()
                .map(item -> item.getSubtotal() != null ? item.getSubtotal() :
                        item.getUnitPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        order.setOrderAmount(total);
        order.setOrderStatus(OrderStatus.PENDING);

        // 3. Guarda la orden (la cascada guardará los items)
        return orderRepository.save(order);
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));
    }

    public List<Order> getOrdersByUser(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    @Transactional
    public Order updateOrderStatus(Long id, OrderStatus status) {
        Order order = getOrderById(id);
        order.setOrderStatus(status);
        return orderRepository.save(order);
    }
}