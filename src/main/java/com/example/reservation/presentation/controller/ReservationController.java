package com.example.reservation.presentation.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.reservation.application.ReservationUseCase;
import com.example.reservation.application.dto.ReservationOutput;
import com.example.reservation.presentation.dto.ReservationRequest;
import com.example.reservation.presentation.dto.ReservationResponse;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {
	private final ReservationUseCase useCase;
	public ReservationController(ReservationUseCase useCase) {
		this.useCase = useCase;
	}
	
	@GetMapping
	public ResponseEntity<List<ReservationResponse>> findAll(){
		List<ReservationOutput> outputs = useCase.findAll();
		List<ReservationResponse> response = 
				outputs.stream().map(ReservationResponse::fromOutput)
				.collect(Collectors.toList());
		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ResponseEntity<ReservationResponse> create(
			@RequestBody ReservationRequest request
			){
		
		ReservationOutput output = useCase.create(
				request.getResourceId(), 
				request.getUserId(),
				request.getStartTime(),
				request.getEndTime()
				);
		ReservationResponse response = ReservationResponse.fromOutput(output);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	@PutMapping("/{id}/confirm")
	public ResponseEntity<ReservationResponse> confirm(
			@PathVariable Long id
			){
		ReservationOutput output = useCase.confirm(id);
		ReservationResponse response = ReservationResponse.fromOutput(output);
		return ResponseEntity.ok(response);
	}
	
	@PutMapping("/{id}/cancel")
	public ResponseEntity<ReservationResponse> cancel(
			@PathVariable Long id
			){
		ReservationOutput output = useCase.cancel(id);
		ReservationResponse response = ReservationResponse.fromOutput(output);
		return ResponseEntity.ok(response);
	}
	
	@PutMapping("/{id}/complete")
	public ResponseEntity<ReservationResponse> complete(
			@PathVariable Long id
			){
		ReservationOutput output = useCase.complete(id);
		ReservationResponse response = ReservationResponse.fromOutput(output);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/resource/{resourceId}")
	public ResponseEntity<List<ReservationResponse>> findByResourceId(
			@PathVariable Long resourceId
			){
		List<ReservationOutput> outputs = useCase.findByResourceId(resourceId);
		List<ReservationResponse> response = 
				outputs.stream()
				.map(ReservationResponse::fromOutput)
				.collect(Collectors.toList());
		return ResponseEntity.ok(response);
	}
	
	
}
