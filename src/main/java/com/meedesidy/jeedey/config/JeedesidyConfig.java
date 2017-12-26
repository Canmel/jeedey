package com.meedesidy.jeedey.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.meedesidy.jeedey.aop.ValidatorAspect;
import com.meedesidy.jeedey.interceptor.PjaxInterceptor;

@Configuration
@ComponentScan("com.meedesidy.jeedey")
public class JeedesidyConfig extends WebMvcConfigurerAdapter{
	
	@Bean
	public ApplicationStringConfig getApplicationStringConfig() {
		return new ApplicationStringConfig();
	}
	
	@Bean
	public ValidatorAspect getValidatorAspect() {
		return new ValidatorAspect();
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new PjaxInterceptor());
	}
	
}
