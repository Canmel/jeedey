package com.meedesidy.jeedey.interceptor.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class FinalExceptionHandler{

	private static final String ERROR_PAGE = "error"; 
	
	@ExceptionHandler(Exception.class)
    public ModelAndView error(Exception e) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName(ERROR_PAGE);
        mv.setStatus(HttpStatus.NOT_FOUND);
        return mv;
    }
	
	@ExceptionHandler(RuntimeException.class)
    public ModelAndView runtime(Exception e) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName(ERROR_PAGE);
        mv.setStatus(HttpStatus.NOT_FOUND);
        return mv;
    }
}
