package com.example.reservation.application;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.reservation.application.dto.ReservationOutput;
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
	
	public ReservationOutput create(
			Long resourceId,
			Long userId,
			LocalDateTime startTime,
			LocalDateTime endTime
			) {
		ResourceId rId = new ResourceId(resourceId);
		UserId uId = new UserId(userId);
		TimeSlot timeSlot = new TimeSlot(startTime, endTime);
		Reservation reservation = new Reservation(
				null,
				rId,
				uId,
				timeSlot,
				ReservationStatus.PENDING
				);
		Reservation saved = repository.save(reservation);
		return ReservationOutput.fromEntity(saved);
	}
	public List<ReservationOutput> findAll() {
		return repository.findAll().stream().map(ReservationOutput::fromEntity)
				.collect(Collectors.toList());
	}
	
	public ReservationOutput confirm(Long id) {
		ReservationId rId = new ReservationId(id);
		Reservation reservation =repository.findById(rId)
		.orElseThrow(
				()->new ReservationNotFoundException("予約が見つかりません: " + id));
		reservation.confirm();
		Reservation saved = repository.save(reservation);
		return ReservationOutput.fromEntity(saved);
	}
	
	public ReservationOutput cancel( Long id) {
		ReservationId rId = new ReservationId(id);
		Reservation reservation =repository.findById(rId)
		.orElseThrow(
				()->new ReservationNotFoundException("予約が見つかりません: " + id));
		reservation.cancel();
		Reservation saved = repository.save(reservation);
		return ReservationOutput.fromEntity(saved);
	}
	
	public ReservationOutput complete(Long id) {
		ReservationId rId = new ReservationId(id);
		Reservation reservation =repository.findById(rId)
		.orElseThrow(
				()->new ReservationNotFoundException("予約が見つかりません: " + id));
		reservation.complete();
		Reservation saved = repository.save(reservation);
		return ReservationOutput.fromEntity(saved);
	}
	
	public List<ReservationOutput> findByResourceId(Long id){
		ResourceId resourceId = new ResourceId(id);
		return repository.findByResourceId(resourceId).stream().map(ReservationOutput::fromEntity)
				.collect(Collectors.toList());
	}
	

}
