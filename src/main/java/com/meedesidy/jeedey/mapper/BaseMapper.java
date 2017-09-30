package com.meedesidy.jeedey.mapper;

import java.util.List;

import com.meedesidy.jeedey.entity.BaseEntity;

public interface BaseMapper {
	
	public int insert(BaseEntity user);

	public BaseEntity getEntity(BaseEntity entity);
	
	public List<BaseEntity> pageQuery(BaseEntity entity);
}
