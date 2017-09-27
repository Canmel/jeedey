package com.meedesidy.jeedey.controllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.meedesidy.jeedey.entity.User;

@Controller
@RequestMapping(value = "/users")
public class UsersController extends BaseController{
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index(Model model) throws JsonProcessingException {
		LoggerFactory.getLogger(UsersController.class).info("我就是来记录这个日志 ");
		User user = new User("Meedesidy", "lidejian@skio.cn", "18357162602");
		List<User> users = new ArrayList<User>();
		users.add(user);
		model.addAttribute("user", user);
		model.addAttribute("users", users);
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
	public String save(Model model, User user) {
		LoggerFactory.getLogger(UsersController.class).info("我就是save() ");
		System.out.println(user.getEmail());
		System.out.println(user.getName());
		System.out.println(user.getPhone());
		return "/users/index";
	}
}
