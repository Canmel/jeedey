package com.meedesidy.jeedey.interceptor;

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

public class PjaxInterceptor implements HandlerInterceptor {

	private static String[] DEFINE_PATH = { "/error", "/notFound", "/refused" };

	@Autowired
	private ApplicationStringConfig applicationStringConfig;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
		System.out.println("请求:　　" + request.getMethod() + request.getRequestURI().toString());
		applicationStringConfig = (ApplicationStringConfig) factory.getBean("getApplicationStringConfig");
		// 当请求地址是已知的DEFINE_PATH中定义的地址时，允许返回true
		if (ArrayUtils.contains(DEFINE_PATH, request.getRequestURI().toString()) || isExport(request.getRequestURI().toString())) {
			return true;
		}else if (!isPjaxRequest(request)
				&& !request.getRequestURI().toString().equals(applicationStringConfig.getContent_path())) {
			// 当请求不是一个pjax请求并且不是主项目目录重定向出去到项目根目录
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

	/**
	 * 判断是否是一个Pjax请求
	 * @param request
	 * @return
	 */
	private boolean isPjaxRequest(HttpServletRequest request) {
		return !StringUtils.isEmpty(request.getParameter("_pjax"));
	}
	
	/**
	 * 判断是否是一个导出请求
	 * @param url 
	 * @return
	 */
	private boolean isExport(String url) {
		return url.endsWith("export");
	}

}
