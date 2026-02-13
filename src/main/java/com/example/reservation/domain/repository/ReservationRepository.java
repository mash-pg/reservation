package com.example.reservation.domain.repository;

import java.util.List;
import java.util.Optional;

import com.example.reservation.domain.entity.Reservation;
import com.example.reservation.domain.valueobject.ReservationId;
import com.example.reservation.domain.valueobject.ResourceId;

public interface ReservationRepository {
	Reservation save(Reservation reservation);
	Optional<Reservation> findById(ReservationId id);
	List<Reservation> findAll();
	List<Reservation> findByResourceId(ResourceId resourceId);
}
