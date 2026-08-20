package com.shipdist.app.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum PackageType {
    DOCUMENT, PARCEL, FRAGILE, BULK;

    @JsonValue
    public String toJson() {
        return name().toLowerCase();
    }

    @JsonCreator
    public static PackageType fromJson(String value) {
        return PackageType.valueOf(value.trim().toUpperCase());
    }
}
