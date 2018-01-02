package com.meedesidy.jeedey.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "")
public class ApplicationController {
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index() {
		return "layout/application";
	}
	
}
