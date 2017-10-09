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
import com.meedesidy.jeedey.entity.User;
import com.meedesidy.jeedey.entity.enums.Status;
import com.meedesidy.jeedey.service.UserService;

@Controller
@RequestMapping(value = "/users")
public class MenusController extends BaseController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index(Model model, User user) throws JsonProcessingException {
		user.setStatus(Status.active);
		return super.index(model, user);
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public String edit(@PathVariable Integer id, Model model) {
		return super.edit(id, model);
	}

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String create(Model model, User user) {
		return super.create(model, user);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public void update(Model model, User user, HttpServletResponse resp) throws IOException {
		super.update(model, user, resp);
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public void save(Model model, User user, HttpServletResponse resp) throws IOException {
		super.save(model, user, resp);
	}
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public void delete(Model model, @PathVariable Integer id, HttpServletResponse resp) throws IOException {
		super.delete(model, id, resp);
	}

	@Override
	public UserService getService() {
		return userService;
	}
	
	@Override
	public String getContentPath() {
		return "/users";
	}
}
