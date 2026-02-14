package com.example.reservation.infrastructure.entity;

import com.example.reservation.domain.entity.Resource;
import com.example.reservation.domain.valueobject.ResourceId;
import com.example.reservation.domain.valueobject.ResourceName;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="resources")
@Getter
@Setter
public class ResourceJpaEntity {
	//フィールド(id,name)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private  Long id;
	private String name;

	public ResourceJpaEntity() {};
	
	public ResourceJpaEntity(Long id,String name) {
		this.id = id;
		this.name = name;
	}
	
	//JpaEntity → Domain Entity
	public Resource toDomain() {
		return new Resource(
			new ResourceId(id),
			new ResourceName(name)
			);
	}
	//Domain Entity → JpaEntity
	public static ResourceJpaEntity fromDomain(Resource resource) {
		Long id = (resource.getId() != null) ? resource.getId().getId() : null;
		String name = resource.getName().getName();
		
		return new ResourceJpaEntity(
					id,name
				);
	}
}
