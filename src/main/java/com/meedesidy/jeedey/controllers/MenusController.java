package com.meedesidy.jeedey.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.meedesidy.jeedey.entity.Menu;
import com.meedesidy.jeedey.entity.enums.Status;
import com.meedesidy.jeedey.service.MenuService;

@Controller
@RequestMapping(value = "/menus")
public class MenusController extends BaseController {

	@Autowired
	private MenuService menuService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index(Model model, Menu menu) throws JsonProcessingException {
		menu.setStatus(Status.active);
		return super.index(model, menu);
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public String edit(@PathVariable Integer id, Model model) {
		return super.edit(id, model);
	}

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String create(Model model, Menu menu) {
		return super.create(model, menu);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public void update(Model model, Menu menu, HttpServletResponse resp) throws IOException {
		super.update(model, menu, resp);
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public void save(Model model, Menu menu, HttpServletResponse resp) throws IOException {
		super.save(model, menu, resp);
	}
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public void delete(Model model, @PathVariable Integer id, HttpServletResponse resp) throws IOException {
		super.delete(model, id, resp);
	}

	@Override
	public MenuService getService() {
		return menuService;
	}
	
	@Override
	public String getContentPath() {
		return "/menus";
	}
}
