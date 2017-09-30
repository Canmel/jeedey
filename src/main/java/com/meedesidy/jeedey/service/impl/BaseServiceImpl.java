package com.meedesidy.jeedey.service.impl;

import java.util.List;

import com.meedesidy.jeedey.entity.BaseEntity;
import com.meedesidy.jeedey.mapper.BaseMapper;
import com.meedesidy.jeedey.service.BaseService;

public abstract class BaseServiceImpl implements BaseService{
	public abstract BaseMapper getMapper();
	
	public BaseEntity getEntity(BaseEntity entity) {
		return getMapper().getEntity(entity);
	}
	
	@Override
	public BaseEntity insert(BaseEntity entity) {
		getMapper().insert(entity);
		return getEntity(entity);
	}
	
	@Override
	public List<BaseEntity> pageQuery(BaseEntity entity) {
		return getMapper().pageQuery(entity);
	}
}
