package com.meedesidy.jeedey.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.meedesidy.jeedey.service.BaseService;

@Controller
@RequestMapping("/sessions")
public class SessionsController extends BaseController {

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return getContentPath() + "/login";
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public String save() {
		return "";
	}

	@Override
	public String getContentPath() {
		// TODO Auto-generated method stub
		return "sessions";
	}

	@Override
	public BaseService getService() {
		// TODO Auto-generated method stub
		return null;
	}

}
