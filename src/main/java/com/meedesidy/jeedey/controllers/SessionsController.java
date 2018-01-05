package com.meedesidy.jeedey.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.meedesidy.jeedey.entity.User;
import com.meedesidy.jeedey.service.BaseService;
import com.meedesidy.jeedey.service.SessionService;

@Controller
@RequestMapping("/sessions")
public class SessionsController extends BaseController {

	@Autowired
	private SessionService sessionService;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return getContentPath() + "/login";
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public void save(HttpSession session, User user, HttpServletResponse resp) throws Exception {
		List<User> list = sessionService.doLogin(user);
		if(!list.isEmpty()) {
			session.setAttribute("currentUser", list.get(0));
		}
		if(!list.isEmpty()) {
			resp.sendRedirect("");
		}else {
			resp.sendRedirect("sessions/login");
		}
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public void deleteHttp(HttpSession session, HttpServletResponse resp) throws IOException {
		session.removeAttribute("currentUser");
		resp.sendRedirect("/sessions/login");
	}
	
	@Override
	public String getContentPath() {
		// TODO Auto-generated method stub
		return "sessions";
	}

	@Override
	public BaseService getService() {
		return sessionService;
	}

}
