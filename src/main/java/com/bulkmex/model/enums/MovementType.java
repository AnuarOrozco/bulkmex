package com.bulkmex.model.enums;

public enum MovementType {
    INBOUND,    // Stock increase (purchases, returns)
    OUTBOUND,   // Stock decrease (sales, waste)
    ADJUSTMENT // Manual corrections
}
