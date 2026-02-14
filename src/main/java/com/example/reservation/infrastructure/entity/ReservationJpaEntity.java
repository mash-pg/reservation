package com.example.reservation.infrastructure.entity;

import java.time.LocalDateTime;

import com.example.reservation.domain.entity.Reservation;
import com.example.reservation.domain.valueobject.ReservationId;
import com.example.reservation.domain.valueobject.ReservationStatus;
import com.example.reservation.domain.valueobject.ResourceId;
import com.example.reservation.domain.valueobject.TimeSlot;
import com.example.reservation.domain.valueobject.UserId;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="reservations")
@Getter
@Setter
public class ReservationJpaEntity {
	//フィールド(id,name)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private  Long id;
	private  Long resourceId;
	private  Long userId;
	private  LocalDateTime startTime;
	private  LocalDateTime endTime;
	@Enumerated(EnumType.STRING)
	private ReservationStatus status;

	public ReservationJpaEntity() {};
	
	public ReservationJpaEntity(
			Long id,
			Long resourceId,
			Long userId,
			LocalDateTime startTime,
			LocalDateTime endTime,
			ReservationStatus status
			) {
		this.id = id;
		this.resourceId = resourceId;
		this.userId = userId;
		this.startTime = startTime;
		this.endTime = endTime;
		this.status = status;
	}
	
	//JpaEntity → Domain Entity
	public Reservation toDomain() {
		return new Reservation(
			new ReservationId(id),
			new ResourceId(resourceId),
			new UserId(userId),
			new TimeSlot(startTime,endTime),
			status
			);
	}
	//Domain Entity → JpaEntity
	public static ReservationJpaEntity fromDomain(Reservation reservation) {
		Long id = (reservation.getId() != null) ? reservation.getId().getId() : null;
		Long resourceId = reservation.getResourceId().getId();
		Long userId = reservation.getUserId().getId();
		LocalDateTime startTime = reservation.getTimeSlot().getStartTime();
		LocalDateTime endTime = reservation.getTimeSlot().getEndTime();
		ReservationStatus status = reservation.getStatus();
		return new ReservationJpaEntity(
					id,
					resourceId,
					userId,
					startTime,
					endTime,
					status
				);
	}
}
