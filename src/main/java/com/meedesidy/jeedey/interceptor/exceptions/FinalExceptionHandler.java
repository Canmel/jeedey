package com.meedesidy.jeedey.interceptor.exceptions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class FinalExceptionHandler implements ErrorController{

	private static final String ERROR_PAGE = "error"; 
	
	@Override
	public String getErrorPath() {
		// TODO Auto-generated method stub
		return ERROR_PAGE;
	}
	
	@RequestMapping(value = ERROR_PAGE)
    public ModelAndView error(HttpServletResponse resp, HttpServletRequest req) {
		System.out.println("222222222222222222");
        ModelAndView mv = new ModelAndView();
        mv.setViewName(ERROR_PAGE);
        mv.setStatus(HttpStatus.NOT_FOUND);
        return mv;
    }
}
