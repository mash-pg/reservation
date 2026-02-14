package com.example.reservation.infrastructure.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.example.reservation.domain.entity.Reservation;
import com.example.reservation.domain.repository.ReservationRepository;
import com.example.reservation.domain.valueobject.ReservationId;
import com.example.reservation.domain.valueobject.ResourceId;
import com.example.reservation.infrastructure.entity.ReservationJpaEntity;

@Repository
public class ReservationRepositoryImpl implements ReservationRepository{
	
	private final ReservationJpaRepository jpaRepository;
	public ReservationRepositoryImpl(ReservationJpaRepository jpaRepository) {
		this.jpaRepository = jpaRepository;
	}
	@Override
	public Reservation save(Reservation reservation) {
		ReservationJpaEntity jpaEntity = ReservationJpaEntity.fromDomain(reservation);
		ReservationJpaEntity saved = jpaRepository.save(jpaEntity);
		
		return saved.toDomain();
	}
	@Override
	public Optional<Reservation> findById(ReservationId id) {
		Optional<ReservationJpaEntity> jparepository = jpaRepository.findById(id.getId());
		return jparepository.map(jpa-> jpa.toDomain());
	}
	@Override
	public List<Reservation> findAll() {
		List<ReservationJpaEntity> jparepository = jpaRepository.findAll();
		return jparepository.stream()
				.map(jpa->jpa.toDomain())
				.collect(Collectors.toList());
	}
	@Override
	public List<Reservation> findByResourceId(ResourceId resourceId) {
		List<ReservationJpaEntity> jparepository = jpaRepository.findByResourceId(resourceId.getId());
		return jparepository.stream().map(jpa-> jpa.toDomain()).collect(Collectors.toList());
	}
}
