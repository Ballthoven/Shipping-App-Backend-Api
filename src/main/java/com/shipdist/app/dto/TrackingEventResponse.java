package com.shipdist.app.dto;

import com.shipdist.app.entity.ShipmentStatus;
import com.shipdist.app.entity.TrackingEvent;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class TrackingEventResponse {
    private UUID id;
    private ShipmentStatus status;
    private String location;
    private String note;
    private LocalDate occurredAt;
    private LocalDateTime createdAt;

    public static TrackingEventResponse from(TrackingEvent e) {
        TrackingEventResponse r = new TrackingEventResponse();
        r.id = e.getId();
        r.status = e.getStatus();
        r.location = e.getLocation();
        r.note = e.getNote();
        r.occurredAt = e.getOccurredAt();
        r.createdAt = e.getCreatedAt();
        return r;
    }

    public UUID getId() { return id; }
    public ShipmentStatus getStatus() { return status; }
    public String getLocation() { return location; }
    public String getNote() { return note; }
    public LocalDate getOccurredAt() { return occurredAt; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}
