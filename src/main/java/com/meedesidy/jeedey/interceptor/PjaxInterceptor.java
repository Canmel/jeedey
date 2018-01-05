package com.meedesidy.jeedey.interceptor;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.ArrayUtils;
import org.thymeleaf.util.StringUtils;

import com.meedesidy.jeedey.config.ApplicationStringConfig;
import com.meedesidy.jeedey.entity.User;
import com.netflix.infix.lang.infix.antlr.EventFilterParser.boolean_expr_return;

public class PjaxInterceptor implements HandlerInterceptor {

	@Autowired
	private ApplicationStringConfig applicationStringConfig;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println(request.getRequestURI());

		User currentUser = (User) request.getSession().getAttribute("currentUser");

		boolean isLogin = currentUser != null;

		// 判断是否是根目录
		boolean isRoot = isRootUrl(request.getRequestURI());
		if (isRoot) {
			if (isLogin) {
				return true;
			} else {
				response.sendRedirect("/sessions/login");
				return false;
			}
		}

		// 判断是否是需要放过的
		if (exceptPjaxUrl().contains(request.getRequestURI())) {
			return true;
		}

		// 判断是否是Pjax请求
		if (!isPjaxRequest(request)) {
			response.sendRedirect("/error");
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

	/**
	 * 判断是否是一个Pjax请求
	 * 
	 * @param request
	 * @return
	 */
	private boolean isPjaxRequest(HttpServletRequest request) {
		return !StringUtils.isEmpty(request.getParameter("_pjax"));
	}

	/**
	 * 判断是否是一个导出请求
	 * 
	 * @param url
	 * @return
	 */
	private boolean isExport(String url) {
		return url.endsWith("export");
	}

	/**
	 * 判断是否是一个根目录
	 * 
	 * @param url
	 * @return
	 */
	private boolean isRootUrl(String url) {
		return url.equals("/");
	}

	public List<String> exceptPjaxUrl() {
		List<String> list = new ArrayList<String>();

		list.add("/error");
		list.add("/sessions/login");
		list.add("/sessions/logout");
		list.add("/sessions");
		return list;
	}
}
