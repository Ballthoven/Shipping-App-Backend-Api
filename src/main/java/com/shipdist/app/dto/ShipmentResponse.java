package com.shipdist.app.dto;

import com.shipdist.app.entity.PackageType;
import com.shipdist.app.entity.Shipment;
import com.shipdist.app.entity.ShipmentStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class ShipmentResponse {
    private UUID id;
    private String trackingId;
    private UUID userId;
    private String originAddress;
    private String destinationAddress;
    private String recipientName;
    private String recipientPhone;
    private PackageType packageType;
    private String notes;
    private ShipmentStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<TrackingEventResponse> events;

    public static ShipmentResponse from(Shipment s) {
        ShipmentResponse r = new ShipmentResponse();
        r.id = s.getId();
        r.trackingId = s.getTrackingId();
        r.userId = s.getUser() != null ? s.getUser().getId() : null;
        r.originAddress = s.getOriginAddress();
        r.destinationAddress = s.getDestinationAddress();
        r.recipientName = s.getRecipientName();
        r.recipientPhone = s.getRecipientPhone();
        r.packageType = s.getPackageType();
        r.notes = s.getNotes();
        r.status = s.getStatus();
        r.createdAt = s.getCreatedAt();
        r.updatedAt = s.getUpdatedAt();
        r.events = s.getEvents().stream()
                .map(TrackingEventResponse::from)
                .collect(Collectors.toList());
        return r;
    }

    public UUID getId() { return id; }
    public String getTrackingId() { return trackingId; }
    public UUID getUserId() { return userId; }
    public String getOriginAddress() { return originAddress; }
    public String getDestinationAddress() { return destinationAddress; }
    public String getRecipientName() { return recipientName; }
    public String getRecipientPhone() { return recipientPhone; }
    public PackageType getPackageType() { return packageType; }
    public String getNotes() { return notes; }
    public ShipmentStatus getStatus() { return status; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public List<TrackingEventResponse> getEvents() { return events; }
}
