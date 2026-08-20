package com.shipdist.app.service;

import com.shipdist.app.dto.ShipmentRequest;
import com.shipdist.app.dto.TrackingEventRequest;
import com.shipdist.app.entity.Shipment;
import com.shipdist.app.entity.ShipmentStatus;
import com.shipdist.app.entity.TrackingEvent;
import com.shipdist.app.entity.User;
import com.shipdist.app.exception.ResourceNotFoundException;
import com.shipdist.app.repository.ShipmentRepository;
import com.shipdist.app.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;
import java.util.UUID;

@Service
public class ShipmentService {

    private static final String DIGITS = "0123456789";
    private static final SecureRandom RANDOM = new SecureRandom();

    private final ShipmentRepository shipmentRepository;
    private final UserRepository userRepository;

    public ShipmentService(ShipmentRepository shipmentRepository, UserRepository userRepository) {
        this.shipmentRepository = shipmentRepository;
        this.userRepository = userRepository;
    }

    public List<Shipment> findAll() {
        return shipmentRepository.findAll();
    }

    public List<Shipment> findByStatus(ShipmentStatus status) {
        return shipmentRepository.findByStatus(status);
    }

    public Shipment findById(UUID id) {
        return shipmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Shipment not found: " + id));
    }

    public Shipment findByTrackingId(String trackingId) {
        return shipmentRepository.findByTrackingId(trackingId)
                .orElseThrow(() -> new ResourceNotFoundException("No shipment with tracking ID: " + trackingId));
    }

    public Shipment create(ShipmentRequest request, String creatorEmail) {
        User creator = userRepository.findByEmail(creatorEmail)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + creatorEmail));

        Shipment shipment = new Shipment();
        shipment.setTrackingId(generateTrackingId());
        shipment.setUser(creator);
        shipment.setOriginAddress(request.getOriginAddress());
        shipment.setDestinationAddress(request.getDestinationAddress());
        shipment.setRecipientName(request.getRecipientName());
        shipment.setRecipientPhone(request.getRecipientPhone());
        shipment.setPackageType(request.getPackageType());
        shipment.setNotes(request.getNotes());
        shipment.setStatus(ShipmentStatus.PENDING);

        Shipment saved = shipmentRepository.save(shipment);

        TrackingEvent initialEvent = new TrackingEvent();
        initialEvent.setShipment(saved);
        initialEvent.setStatus(ShipmentStatus.PENDING);
        initialEvent.setLocation(request.getOriginAddress());
        initialEvent.setNote("Shipment created");
        saved.getEvents().add(initialEvent);

        return shipmentRepository.save(saved);
    }

    public Shipment addTrackingEvent(UUID shipmentId, TrackingEventRequest request) {
        Shipment shipment = findById(shipmentId);

        TrackingEvent event = new TrackingEvent();
        event.setShipment(shipment);
        event.setStatus(request.getStatus());
        event.setLocation(request.getLocation());
        event.setNote(request.getNote());
        event.setOccurredAt(request.getOccurredAt());

        shipment.getEvents().add(event);
        shipment.setStatus(request.getStatus());

        return shipmentRepository.save(shipment);
    }

    public void delete(UUID id) {
        shipmentRepository.delete(findById(id));
    }

    private String generateTrackingId() {
        StringBuilder sb = new StringBuilder("SF");
        for (int i = 0; i < 4; i++) {
            sb.append(DIGITS.charAt(RANDOM.nextInt(DIGITS.length())));
        }
        String candidate = sb.toString();
        return shipmentRepository.existsByTrackingId(candidate) ? generateTrackingId() : candidate;
    }
}
