package com.bulkmex.controller;

import com.bulkmex.model.Inventory;
import com.bulkmex.model.enums.MovementType;
import com.bulkmex.service.InventoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping("/movement")
    public ResponseEntity<Inventory> recordMovement(
            @RequestParam Long productId,
            @RequestParam Integer quantity,
            @RequestParam MovementType movementType,
            @RequestParam String reason) {
        return ResponseEntity.ok(
                inventoryService.recordMovement(productId, quantity, movementType, reason)
        );
    }
}