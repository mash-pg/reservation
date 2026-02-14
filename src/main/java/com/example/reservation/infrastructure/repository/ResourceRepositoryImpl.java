package com.example.reservation.infrastructure.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.example.reservation.domain.entity.Resource;
import com.example.reservation.domain.repository.ResourceRepository;
import com.example.reservation.domain.valueobject.ResourceId;
import com.example.reservation.infrastructure.entity.ResourceJpaEntity;

@Repository
public class ResourceRepositoryImpl implements ResourceRepository {
	private final ResourceJpaRepository jpaRepository;
	
	public ResourceRepositoryImpl(ResourceJpaRepository jpaRepository) {
		this.jpaRepository = jpaRepository;
	}
	@Override
	public List<Resource> findAll(){
		List<ResourceJpaEntity> jparepository = jpaRepository.findAll();
		return jparepository.stream()
				.map(jpa->jpa.toDomain())
				.collect(Collectors.toList());
	}
	@Override
	public Resource save(Resource resource){
		ResourceJpaEntity jpaEntity = ResourceJpaEntity.fromDomain(resource);
		ResourceJpaEntity saved = jpaRepository.save(jpaEntity);
		
		return saved.toDomain();
	}
	
	@Override
	public Optional<Resource> findById(ResourceId id) {
		Optional<ResourceJpaEntity> jparepository = jpaRepository.findById(id.getId());
		return jparepository.map(jpa-> jpa.toDomain());
	}
}
