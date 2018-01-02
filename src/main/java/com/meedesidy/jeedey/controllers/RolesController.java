package com.meedesidy.jeedey.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.meedesidy.jeedey.entity.Role;
import com.meedesidy.jeedey.entity.enums.Status;
import com.meedesidy.jeedey.service.RoleService;

@Controller
@RequestMapping(value = "roles")
public class RolesController extends BaseController {

	@Autowired
	private RoleService roleService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index(Model model, Role role) throws JsonProcessingException {
		role.setStatus(Status.active);
		return super.index(model, role);
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public String edit(@PathVariable Integer id, Model model) {
		return super.edit(id, model);
	}

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String create(Model model, Role role) {
		return super.create(model, role);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public void update(Model model, Role role, HttpServletResponse resp) throws IOException {
		super.update(model, role, resp);
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public ModelAndView save(Model model, @Valid Role role, BindingResult result, HttpServletResponse resp, HttpServletRequest req) throws IOException {
		if (result.hasErrors()) {
			return getNotValidModelAndView(getContentPath() + "/new", result, role);
		}
		return super.save(model, role, resp, req);
	}
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public void delete(Model model, @PathVariable Integer id, HttpServletResponse resp) throws IOException {
		super.delete(model, id, resp);
	}

	@Override
	public RoleService getService() {
		return roleService;
	}
	
	@Override
	public String getContentPath() {
		return "roles";
	}
}