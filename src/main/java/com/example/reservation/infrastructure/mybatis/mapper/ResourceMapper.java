package com.example.reservation.infrastructure.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.reservation.infrastructure.mybatis.entity.ResourceMybatisEntity;


@Mapper
public interface ResourceMapper {
	List<ResourceMybatisEntity> findAll();
	ResourceMybatisEntity findById(Long id);
	void insert(ResourceMybatisEntity entity);
}
