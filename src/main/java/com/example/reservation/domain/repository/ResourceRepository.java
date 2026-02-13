package com.example.reservation.domain.repository;

import java.util.List;
import java.util.Optional;

import com.example.reservation.domain.entity.Resource;
import com.example.reservation.domain.valueobject.ResourceId;

public interface ResourceRepository {
	Resource save(Resource resource);
	Optional<Resource> findById(ResourceId id);
	List<Resource> findAll();
}
