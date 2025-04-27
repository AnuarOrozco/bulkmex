package com.bulkmex.service;

import com.bulkmex.model.*;
import com.bulkmex.model.enums.MovementType;
import com.bulkmex.repository.InventoryRepository;
import com.bulkmex.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    private final ProductRepository productRepository;

    public InventoryService(InventoryRepository inventoryRepository, ProductRepository productRepository) {
        this.inventoryRepository = inventoryRepository;
        this.productRepository = productRepository;
    }

    public Inventory recordMovement(Long productId, Integer quantity, MovementType movementType, String reason) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Inventory movement = new Inventory();
        movement.setProduct(product);
        movement.setQuantity(quantity);
        movement.setMovementType(movementType);
        movement.setReason(reason);
        movement.setDateTime(LocalDateTime.now());

        // Actualiza el stock del producto
        if (movementType == MovementType.INBOUND) {
            product.setStock(product.getStock() + quantity);
        } else {
            product.setStock(product.getStock() - quantity);
        }

        productRepository.save(product);
        return inventoryRepository.save(movement);
    }
}