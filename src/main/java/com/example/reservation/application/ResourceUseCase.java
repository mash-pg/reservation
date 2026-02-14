package com.example.reservation.application;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.reservation.application.dto.ResourceOutput;
import com.example.reservation.domain.entity.Resource;
import com.example.reservation.domain.exception.ResourceNotFoundException;
import com.example.reservation.domain.repository.ResourceRepository;
import com.example.reservation.domain.valueobject.ResourceId;
import com.example.reservation.domain.valueobject.ResourceName;

@Service
@Transactional
public class ResourceUseCase {
	private final ResourceRepository repository;
	public ResourceUseCase(
			ResourceRepository repository
			) {
		this.repository = repository;
	}
	
	public ResourceOutput create(String name) {
		ResourceName resourceName = new ResourceName(name);
		Resource resource = new Resource(null, resourceName);
		Resource saved = repository.save(resource);
		return ResourceOutput.fromEntity(saved);	
	}
	
	public List<ResourceOutput> findAll(){
		return repository.findAll().stream().map(ResourceOutput::fromEntity)
				.collect(Collectors.toList());
	}
	
	public ResourceOutput findById(ResourceId id){
		Resource resource =  repository.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("任意のResource見つかりません"));
		return ResourceOutput.fromEntity(resource);
	}
}
