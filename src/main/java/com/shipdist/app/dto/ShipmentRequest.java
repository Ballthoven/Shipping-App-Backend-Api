package com.shipdist.app.dto;

import com.shipdist.app.entity.PackageType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ShipmentRequest {

    @NotBlank
    private String originAddress;

    @NotBlank
    private String destinationAddress;

    @NotBlank
    private String recipientName;

    @NotBlank
    private String recipientPhone;

    @NotNull
    private PackageType packageType;

    private String notes;

    public String getOriginAddress() { return originAddress; }
    public void setOriginAddress(String originAddress) { this.originAddress = originAddress; }
    public String getDestinationAddress() { return destinationAddress; }
    public void setDestinationAddress(String destinationAddress) { this.destinationAddress = destinationAddress; }
    public String getRecipientName() { return recipientName; }
    public void setRecipientName(String recipientName) { this.recipientName = recipientName; }
    public String getRecipientPhone() { return recipientPhone; }
    public void setRecipientPhone(String recipientPhone) { this.recipientPhone = recipientPhone; }
    public PackageType getPackageType() { return packageType; }
    public void setPackageType(PackageType packageType) { this.packageType = packageType; }
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}
