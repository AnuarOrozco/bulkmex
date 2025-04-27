package com.bulkmex.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Product name is required")
    @Size(max = 100, message = "Name must be less than 100 characters")
    @Column(nullable = false, length = 100)
    private String name;

    @Size(max = 500)
    private String description;

    @DecimalMin(value = "0.01", message = "Price must be greater than 0")
    @Column(nullable = false, precision = 10, scale = 2)
    private Double price;

    @Min(value = 0, message = "Stock cannot be negative")
    private Integer stock = 0;

    @NotBlank
    private String unit; // kg, liter, box, etc.

    private String brand;

    @NotBlank
    @Column(length = 50)
    private String category;

    private String imageUrl;

    @Column(nullable = false)
    private Boolean active = true;

    // Relationships
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Inventory> inventoryMovements;

}