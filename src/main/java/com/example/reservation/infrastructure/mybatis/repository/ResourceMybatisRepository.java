package com.example.reservation.infrastructure.mybatis.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.example.reservation.domain.entity.Resource;
import com.example.reservation.domain.repository.ResourceRepository;
import com.example.reservation.domain.valueobject.ResourceId;
import com.example.reservation.infrastructure.mybatis.entity.ResourceMybatisEntity;
import com.example.reservation.infrastructure.mybatis.mapper.ResourceMapper;

@Primary //これでこのmybatisを設定する
@Repository("mybatisResourceRepository") 
public class ResourceMybatisRepository implements ResourceRepository{
    private final ResourceMapper mapper;

    public ResourceMybatisRepository(ResourceMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<Resource> findAll() {
        // mapper.findAll() を呼んで、toDomain() で変換
    	List<ResourceMybatisEntity> entities = mapper.findAll();
    	return entities.stream()
    			.map(entity->entity.toDomain())
    			.collect(Collectors.toList());
    }

    @Override
    public Optional<Resource> findById(ResourceId id) {
        // mapper.findById(id.getId()) を呼んで、toDomain() で変換
    	ResourceMybatisEntity entity = mapper.findById(id.getId());
    	return Optional.ofNullable(entity)
    			.map(e -> e.toDomain());
    }

	@Override
	public Resource save(Resource resource) {
		ResourceMybatisEntity entity = ResourceMybatisEntity.fromDomain(resource);
		mapper.insert(entity);
		return entity.toDomain();
	}
}
