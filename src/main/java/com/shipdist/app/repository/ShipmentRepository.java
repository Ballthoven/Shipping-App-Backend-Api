package com.shipdist.app.repository;

import com.shipdist.app.entity.Shipment;
import com.shipdist.app.entity.ShipmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ShipmentRepository extends JpaRepository<Shipment, UUID> {
    Optional<Shipment> findByTrackingId(String trackingId);
    List<Shipment> findByStatus(ShipmentStatus status);
    boolean existsByTrackingId(String trackingId);
}
