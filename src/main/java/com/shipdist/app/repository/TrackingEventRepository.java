package com.shipdist.app.repository;

import com.shipdist.app.entity.TrackingEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TrackingEventRepository extends JpaRepository<TrackingEvent, UUID> {
}
