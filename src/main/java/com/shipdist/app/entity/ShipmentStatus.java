package com.shipdist.app.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ShipmentStatus {
    PENDING, IN_TRANSIT, DELIVERED, CANCELLED;

    @JsonValue
    public String toJson() {
        return name().toLowerCase();
    }

    @JsonCreator
    public static ShipmentStatus fromJson(String value) {
        return ShipmentStatus.valueOf(value.trim().toUpperCase());
    }
}
