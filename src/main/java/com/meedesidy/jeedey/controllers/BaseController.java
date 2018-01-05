package com.meedesidy.jeedey.controllers;

import java.io.IOException;
import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.groovy.tools.shell.util.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.meedesidy.jeedey.entity.BaseEntity;
import com.meedesidy.jeedey.entity.User;
import com.meedesidy.jeedey.entity.enums.Status;
import com.meedesidy.jeedey.interceptor.exceptions.ExcelException;
import com.meedesidy.jeedey.service.BaseService;
import com.meedesidy.jeedey.utils.ExcelJxlUitl;

public abstract class BaseController {

	public MessageSource messageSource;

	public void BeanValidatorConfig(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	public abstract BaseService getService();

	public abstract String getContentPath();

	public PageInfo<BaseEntity> indexData(BaseEntity entity) {
		PageInfo<BaseEntity> page = PageHelper.startPage(entity.getPageIndex(), entity.getPageSize())
				.doSelectPageInfo(() -> getService().pageQuery(entity));
		return page;
	}

	public PageInfo<BaseEntity> indexData() {
		PageInfo<BaseEntity> page = PageHelper.startPage(0, 10)
				.doSelectPageInfo(() -> getService().pageQuery(new BaseEntity()));
		return page;
	}

	public String index(Model model, BaseEntity entity) throws JsonProcessingException {
		model.addAttribute("pageInfo", indexData(entity));
		model.addAttribute("searchEntity", entity);
		return getContentPath() + "/index";
	}

	public String edit(@PathVariable Integer id, Model model) {
		model.addAttribute(getOptName(), getService().getEntity(new User(id)));
		return getContentPath() + "/edit";
	}

	public String create(Model model, BaseEntity entity) {
		try {
			model.addAttribute(getOptName(), entity.getClass().newInstance());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return getContentPath() + "/new";
	}

	public ModelAndView update(Model model, BaseEntity entity, HttpServletResponse resp) throws IOException {
		ModelAndView mv = new ModelAndView();
		getService().update(entity);
		try {
			mv.addObject("pageInfo", indexData(entity.getClass().newInstance()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		mv.setViewName(getContentPath() + "/index");
		return mv;
	}

	@Validated
	public ModelAndView save(Model model, BaseEntity entity, HttpServletResponse resp, HttpServletRequest req)
			throws IOException {
		ModelAndView mv = new ModelAndView();
		getService().insert(entity);
		try {
			mv.addObject("pageInfo", indexData(entity.getClass().newInstance()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		mv.setViewName(getContentPath() + "/index");
		return mv;
	}

	public void delete(Model model, Integer id, HttpServletResponse resp) throws IOException {
		int[] ids = { id };
		getService().delete(ids);
		resp.sendRedirect("/" + getContentPath() + "?_pjax=[data-pjax-container]");
	}

	public ModelAndView getNotValidModelAndView(String viewName, BindingResult result, BaseEntity entity) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName(viewName);
		mv.setStatus(HttpStatus.OK);
		mv.addObject("errors", result.getFieldErrors());
		mv.addObject(entity.getClass().getSimpleName().toLowerCase(), entity);
		return mv;
	}

	public void export(Model model, User entity, LinkedHashMap<String, String> userMap, HttpServletResponse resp)
			throws IOException, ExcelException {
		ExcelJxlUitl.listToExcel(getService().pageQuery(entity), userMap, "excel", resp);
	}

	public String getOptName() {
		return getContentPath().substring(0, getContentPath().length() - 1);
	}
}
