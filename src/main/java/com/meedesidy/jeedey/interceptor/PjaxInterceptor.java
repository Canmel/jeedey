package com.meedesidy.jeedey.interceptor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.StringUtils;

import com.meedesidy.jeedey.config.ApplicationStringConfig;

public class PjaxInterceptor implements HandlerInterceptor {
	
	@Autowired
	private ApplicationStringConfig applicationStringConfig;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
		applicationStringConfig = (ApplicationStringConfig)factory.getBean("getApplicationStringConfig");
		if(!isPjaxRequest(request) && !request.getRequestURI().toString().equals(applicationStringConfig.getContent_path())) {
			System.out.println("非Pjax请求并开始跳转主页");
			response.sendRedirect(applicationStringConfig.getContent_path());
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}
	
	private boolean isPjaxRequest(HttpServletRequest request) {
		return !StringUtils.isEmpty(request.getParameter("_pjax"));
	}

}
