package com.meedesidy.jeedey.controllers;

import java.io.IOException;
import java.util.LinkedHashMap;

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
import com.meedesidy.jeedey.entity.User;
import com.meedesidy.jeedey.entity.enums.Status;
import com.meedesidy.jeedey.interceptor.exceptions.ExcelException;
import com.meedesidy.jeedey.service.UserService;

@Controller
@RequestMapping(value = "users")
public class UsersController extends BaseController {

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
	public ModelAndView update(Model model, @Valid User user, BindingResult result, HttpServletResponse resp) throws IOException {
		if (result.hasErrors()) {
			return getNotValidModelAndView(getContentPath() + "/edit", result, user);
		}
		return super.update(model, user, resp);
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public ModelAndView save(Model model, @Valid User user, BindingResult result, HttpServletResponse resp,
			HttpServletRequest req) throws IOException {
		if (result.hasErrors()) { 
			return getNotValidModelAndView(getContentPath() + "/new", result, user);
		}
		return super.save(model, user, resp, req);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public void delete(Model model, @PathVariable Integer id, HttpServletResponse resp) throws IOException {
		super.delete(model, id, resp);
	}

	@RequestMapping(value = "/export", method = RequestMethod.GET)
	public void export(Model model, User user, HttpServletRequest req, HttpServletResponse resp) throws IOException, ExcelException {
		resp.reset();
		user.setStatus(Status.active);
		super.export(model, user, userMap, resp);
	}
	
	// 导出excel表头
	public final static LinkedHashMap<String, String> userMap = new LinkedHashMap<String, String>();
	static {
		userMap.put("name", "姓名");
		userMap.put("phone", "电话");
		userMap.put("email", "邮箱");
	}

	@Override
	public UserService getService() {
		return userService;
	}

	@Override
	public String getContentPath() {
		return "users";
	}
}
