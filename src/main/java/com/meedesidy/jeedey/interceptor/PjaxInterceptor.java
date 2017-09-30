package com.meedesidy.jeedey.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.StringUtils;

import com.meedesidy.jeedey.config.ApplicationStringConfig;
import com.meedesidy.jeedey.controllers.UsersController;

public class PjaxInterceptor implements HandlerInterceptor {
	
	@Autowired
	private ApplicationStringConfig applicationStringConfig;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
		System.out.println("请求:　　" + request.getRequestURI().toString());
		
		applicationStringConfig = (ApplicationStringConfig)factory.getBean("getApplicationStringConfig");
		if(!isPjaxRequest(request) && !request.getRequestURI().toString().equals(applicationStringConfig.getContent_path())) {
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
		if(!StringUtils.isEmpty(request.getParameter("_pjax"))) {
			System.out.println(request.getParameter("_pjax"));
			System.out.println("pjax请求......");
		}
		return !StringUtils.isEmpty(request.getParameter("_pjax"));
	}

}
