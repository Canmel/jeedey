package com.meedesidy.jeedey.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.meedesidy.jeedey.entity.BaseEntity;
import com.meedesidy.jeedey.entity.User;
import com.meedesidy.jeedey.service.BaseService;

public abstract class BaseController {

	public abstract BaseService getService();
	
	public abstract String getContentPath();
	
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
	
	public String index(Model model, BaseEntity entity) throws JsonProcessingException {
		model.addAttribute("pageInfo", indexData(entity));
		model.addAttribute("searchEntity", entity);
		return getContentPath() + "/index";
	}
	
	public String edit(@PathVariable Integer id, Model model) {
		model.addAttribute("user", getService().getEntity(new User(id)));
		return getContentPath() + "/edit";
	}
	
	public String create(Model model, BaseEntity entity) {
		model.addAttribute("user", new User());
		return getContentPath() + "/new";
	}
	
	public void update(Model model, BaseEntity entity, HttpServletResponse resp) throws IOException {
		getService().update(entity);
		resp.sendRedirect(getContentPath() + "?_pjax=[data-pjax-container]");
	}

	
	public void save(Model model, BaseEntity entity, HttpServletResponse resp) throws IOException {
		getService().insert(entity);
		resp.sendRedirect(getContentPath() + "?_pjax=[data-pjax-container]");
	}
	

	public void delete(Model model, Integer id, HttpServletResponse resp) throws IOException {
		int[] ids = {id};
		getService().delete(ids);
		resp.sendRedirect(getContentPath() + "?_pjax=[data-pjax-container]");
	}
	
}
