package com.meedesidy.jeedey.mapper;

import java.util.List;

import com.meedesidy.jeedey.entity.BaseEntity;

public interface BaseMapper {
	
	public int insert(BaseEntity entity);

	public int update(BaseEntity entity);
	
	public int delete(int[] ids);

	public BaseEntity getEntity(BaseEntity entity);
	
	public List<BaseEntity> pageQuery(BaseEntity entity);
	
}
