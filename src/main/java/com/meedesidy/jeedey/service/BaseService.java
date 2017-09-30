package com.meedesidy.jeedey.service;

import java.util.List;

import com.meedesidy.jeedey.entity.BaseEntity;
import com.meedesidy.jeedey.mapper.BaseMapper;

public interface BaseService {
	
	BaseMapper getMapper();
	
	public BaseEntity insert(BaseEntity entity);
	
	public BaseEntity getEntity(BaseEntity entity);
	
	public List<BaseEntity> pageQuery(BaseEntity entity);
}
