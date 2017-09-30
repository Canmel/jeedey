package com.meedesidy.jeedey.controllers;

import java.util.List;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.meedesidy.jeedey.entity.BaseEntity;
import com.meedesidy.jeedey.service.BaseService;

public abstract class BaseController {

	public abstract BaseService getService();
	
	public PageInfo<BaseEntity> indexData(BaseEntity entity) {
		PageHelper.startPage(entity.getPageIndex(), entity.getPageSize());  
		List<BaseEntity> list = getService().pageQuery(entity);
		return new PageInfo<BaseEntity>(list);
	}
	
	public PageInfo<BaseEntity> indexData(){
		BaseEntity entity = new BaseEntity(); 
		PageHelper.startPage(entity.getPageIndex(), entity.getPageSize());  
		List<BaseEntity> list = getService().pageQuery(entity);
		return new PageInfo<BaseEntity>(list);
	}
}
