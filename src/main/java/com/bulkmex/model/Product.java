package com.bulkmex.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    private String description;

    @Column(nullable = false)
    private Double price;
    private Integer stock;
    private String unit;
    private String brand;

    @Column(length = 50)
    private String category;
    private String image;
    private Boolean active;

}
