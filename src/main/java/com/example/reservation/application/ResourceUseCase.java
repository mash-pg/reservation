package com.example.reservation.application;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	
	public Resource create(ResourceName name) {
		Resource resource = new Resource(null, name);
		return repository.save(resource);
	}
	
	public List<Resource> findAll(){
		return repository.findAll();
	}
	
	public Resource findById(ResourceId id){
		return repository.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("任意のResource見つかりません"));
	}
}
