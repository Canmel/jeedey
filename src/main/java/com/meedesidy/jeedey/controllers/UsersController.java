package com.meedesidy.jeedey.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.meedesidy.jeedey.entity.User;
import com.meedesidy.jeedey.service.UserService;

@Controller
@RequestMapping(value = "/users")
public class UsersController extends BaseController{
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index(Model model, User user, HttpServletRequest req) throws JsonProcessingException {
		LoggerFactory.getLogger(UsersController.class).info("get /users/  参数");
		System.out.println(user.getEmail());
		model.addAttribute("pageInfo", indexData(user));
		model.addAttribute("searchEntity", user);
		return "/users/index";
	}
	
	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public String edit(@PathVariable Integer id, Model model) {
		LoggerFactory.getLogger(UsersController.class).info("我就是getEntity() " + id);
		model.addAttribute("user", new User("张三", "zhangsan@skio.cn", "13876787678"));
		return "/users/edit";
	}
	
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String create(Model model) {
		LoggerFactory.getLogger(UsersController.class).info("我就是create() " );
		model.addAttribute("user", new User());
		return "/users/new";
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public String save(Model model, User user, HttpServletResponse resp) {
		getService().insert(user);
		model.addAttribute("pageInfo", indexData(new User()));
		return "/users/index";
	}	

	@Override
	public UserService getService() {
		return userService;
	}
}
