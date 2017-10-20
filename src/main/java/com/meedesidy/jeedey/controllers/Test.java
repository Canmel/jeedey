package com.meedesidy.jeedey.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.meedesidy.jeedey.entity.User;
import com.meedesidy.jeedey.service.BaseService;
import com.mysql.fabric.xmlrpc.base.Array;

public class Test {
	public static void main(String[] args) throws InstantiationException, IllegalAccessException {
		User user = User.class.newInstance();
		user.setEmail("ssssseeeee");
		Map map = new HashMap();
		map.put((Integer)97, "8778");
		map.put("a", "77887");
		
		WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
		ServletContext sc = wac.getServletContext();
		WebApplicationContext wac2 = WebApplicationContextUtils.getWebApplicationContext(sc);
		WebApplicationContext wac3 = WebApplicationContextUtils.getRequiredWebApplicationContext(sc);
		
		
		System.out.println(map);
	}
	private void helpss() {
		User nnew = new User();
	}
	
}
