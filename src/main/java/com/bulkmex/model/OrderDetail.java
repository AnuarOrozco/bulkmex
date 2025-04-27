package com.bulkmex.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@Entity
@Table(name = "order_details")
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(value = 1, message = "Quantity must be at least 1")
    @Column(nullable = false)
    private Integer quantity;

    @DecimalMin(value = "0.01", message = "Unit price must be positive")
    @Column(nullable = false, precision = 12, scale = 2)
    private Double unitPrice;

    /*
     * Future relationships (uncomment when ready)
     * @ManyToOne(fetch = FetchType.LAZY)
     * @JoinColumn(name = "order_id", nullable = false)
     * private Order order;
     *
     * @ManyToOne(fetch = FetchType.LAZY)
     * @JoinColumn(name = "product_id", nullable = false)
     * private Product product;
     */
}