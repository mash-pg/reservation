package com.example.reservation.infrastructure.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.reservation.infrastructure.entity.ReservationJpaEntity;

public interface ReservationJpaRepository extends JpaRepository<ReservationJpaEntity, Long>{
	 List<ReservationJpaEntity> findByResourceId(Long resourceId);
}
