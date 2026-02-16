package com.example.reservation.infrastructure.mybatis.entity;

import com.example.reservation.domain.entity.Resource;
import com.example.reservation.domain.valueobject.ResourceId;
import com.example.reservation.domain.valueobject.ResourceName;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ResourceMybatisEntity {
	private  Long id;
	private String name;
	
	public ResourceMybatisEntity() {}
	
	public ResourceMybatisEntity(Long id,String name) {
		this.id = id;
		this.name = name;
	}
	
	//MybatisEntity → Domain Entity
	public Resource toDomain() {
		return new Resource(
			new ResourceId(id),
			new ResourceName(name)
			);
	}
	//Domain Entity → MybatisEntity
	public static ResourceMybatisEntity fromDomain(Resource resource) {
		Long id = (resource.getId() != null) ? resource.getId().getId() : null;
		String name = resource.getName().getName();
		
		return new ResourceMybatisEntity(
					id,name
				);
	}
}
