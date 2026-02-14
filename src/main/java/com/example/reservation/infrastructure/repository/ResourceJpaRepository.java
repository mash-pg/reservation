package com.example.reservation.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.reservation.infrastructure.entity.ResourceJpaEntity;

public interface ResourceJpaRepository extends JpaRepository<ResourceJpaEntity,Long>{
}
