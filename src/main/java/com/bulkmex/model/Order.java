package com.bulkmex.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true, length = 20)
    private String orderId;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime orderDate = LocalDateTime.now();

    @DecimalMin(value = "0.01")
    @Column(nullable = false, precision = 12, scale = 2)
    private Double orderAmount;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private OrderStatus orderStatus;

    @NotBlank
    @Column(nullable = false, length = 200)
    private String deliveryAddress;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private PaymentMethod paymentMethod;

    /*
        Many to one with User
        One to many with OrderDetail
     */

}