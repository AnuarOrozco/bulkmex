package com.bulkmex.model;

import com.bulkmex.model.enums.PaymentStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DecimalMin(value = "0.01", message = "Amount must be positive")
    @Column(nullable = false, precision = 12, scale = 2)
    private Double amount;

    @NotNull
    @Column(nullable = false, updatable = false)
    private LocalDateTime date = LocalDateTime.now();

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private PaymentStatus status = PaymentStatus.PENDING;

    @NotBlank
    @Column(nullable = false, unique = true, length = 50)
    private String transactionId;

    // Relationships -----------
    @OneToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

}