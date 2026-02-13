package com.example.reservation.application;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.reservation.domain.entity.Reservation;
import com.example.reservation.domain.exception.ReservationNotFoundException;
import com.example.reservation.domain.repository.ReservationRepository;
import com.example.reservation.domain.valueobject.ReservationId;
import com.example.reservation.domain.valueobject.ReservationStatus;
import com.example.reservation.domain.valueobject.ResourceId;
import com.example.reservation.domain.valueobject.TimeSlot;
import com.example.reservation.domain.valueobject.UserId;

@Service
@Transactional
public class ReservationUseCase {
	private final ReservationRepository repository;
	public ReservationUseCase(
			ReservationRepository repository
			) {
		this.repository = repository;
	}
	
	public Reservation create(
			ResourceId id,
			UserId userId,
			TimeSlot timeSlot
			) {
		Reservation reservation = new Reservation(
				null, 
				id, 
				userId, 
				timeSlot, 
				ReservationStatus.PENDING
				);
		return repository.save(reservation);
	}
	
	public Reservation confirm(ReservationId id) {
		Reservation reservation =repository.findById(id)
		.orElseThrow(
				()->new ReservationNotFoundException("任意のIDではないです"));
		reservation.confirm();
		return repository.save(reservation);
	}
	public Reservation cancel(ReservationId id) {
		Reservation reservation =repository.findById(id)
		.orElseThrow(
				()->new ReservationNotFoundException("任意のIDではないです"));
		reservation.cancel();
		return repository.save(reservation);
	}
	public Reservation complete(ReservationId id) {
		Reservation reservation =repository.findById(id)
		.orElseThrow(
				()->new ReservationNotFoundException("任意のIDではないです"));
		reservation.complete();
		return repository.save(reservation);
	}
	public List<Reservation> findByResourceId(ResourceId id){
		return repository.findByResourceId(id);
	}
	
	public List<Reservation> findAll() {
		return repository.findAll();
	}
}
