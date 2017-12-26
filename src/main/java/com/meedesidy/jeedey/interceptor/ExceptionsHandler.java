package com.meedesidy.jeedey.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionsHandler {

	public static final String DEFFAULT_ERROR_VIEW = "error";
	
	public ModelAndView defaultErrorHandler(HttpServletRequest request, HttpServletResponse response, Exception e) {
		ModelAndView mv = new ModelAndView();
		e.getClass();
		mv.addObject("exception", e);
		mv.setStatus(HttpStatus.FORBIDDEN);
		mv.addObject("url", request.getRequestURL());
		mv.setViewName(DEFFAULT_ERROR_VIEW);
		return mv;
		
	}
}
