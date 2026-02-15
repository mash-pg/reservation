package com.example.reservation.presentation.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.reservation.application.ResourceUseCase;
import com.example.reservation.application.dto.ResourceOutput;
import com.example.reservation.presentation.dto.ResourceRequest;
import com.example.reservation.presentation.dto.ResourceResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/resources")
public class ResourceController {
	private final ResourceUseCase useCase;
	public ResourceController(ResourceUseCase useCase) {
		this.useCase = useCase;
	}
	
	@GetMapping
	public ResponseEntity<List<ResourceResponse>> findAll(){
		List<ResourceOutput> outputs = useCase.findAll();
		List<ResourceResponse> response = 
				outputs.stream()
				.map(ResourceResponse::fromOutput)
				.collect(Collectors.toList());
		return ResponseEntity.ok(response);
	}
	
	@PostMapping
	public ResponseEntity<ResourceResponse> create(
		@Valid	@RequestBody ResourceRequest request
			){
		ResourceOutput output = useCase.create(request.getName());
		ResourceResponse response = ResourceResponse.fromOutput(output);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	
}
