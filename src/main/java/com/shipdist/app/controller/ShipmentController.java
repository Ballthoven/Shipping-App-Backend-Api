package com.shipdist.app.controller;

import com.shipdist.app.dto.ShipmentRequest;
import com.shipdist.app.dto.ShipmentResponse;
import com.shipdist.app.dto.TrackingEventRequest;
import com.shipdist.app.entity.Shipment;
import com.shipdist.app.entity.ShipmentStatus;
import com.shipdist.app.service.ShipmentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/shipments")
public class ShipmentController {

    private final ShipmentService shipmentService;

    public ShipmentController(ShipmentService shipmentService) {
        this.shipmentService = shipmentService;
    }

    @GetMapping
    public List<ShipmentResponse> findAll(@RequestParam(required = false) ShipmentStatus status) {
        List<Shipment> shipments = status != null ? shipmentService.findByStatus(status) : shipmentService.findAll();
        return shipments.stream().map(ShipmentResponse::from).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ShipmentResponse findById(@PathVariable UUID id) {
        return ShipmentResponse.from(shipmentService.findById(id));
    }

    @GetMapping("/track/{trackingId}")
    public ShipmentResponse track(@PathVariable String trackingId) {
        return ShipmentResponse.from(shipmentService.findByTrackingId(trackingId));
    }

    @PostMapping
    public ResponseEntity<ShipmentResponse> create(@Valid @RequestBody ShipmentRequest request,
                                                    Authentication authentication) {
        Shipment created = shipmentService.create(request, authentication.getName());
        return ResponseEntity.ok(ShipmentResponse.from(created));
    }

    @PostMapping("/{id}/events")
    public ShipmentResponse addEvent(@PathVariable UUID id, @Valid @RequestBody TrackingEventRequest request) {
        return ShipmentResponse.from(shipmentService.addTrackingEvent(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        shipmentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
