package com.meedesidy.jeedey.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.github.pagehelper.PageHelper;
import com.meedesidy.jeedey.aop.ValidatorAspect;
import com.meedesidy.jeedey.interceptor.PjaxInterceptor;

@Configuration
@ComponentScan("com.meedesidy.jeedey")
public class JeedesidyConfig extends WebMvcConfigurerAdapter {

	@Bean
	public ApplicationStringConfig getApplicationStringConfig() {
		return new ApplicationStringConfig();
	}

	@Bean
	public ValidatorAspect getValidatorAspect() {
		return new ValidatorAspect();
	}

	@Bean
	public PageHelper getPageHelper() {
		PageHelper pageHelper = new PageHelper();
		Properties properties = new Properties();
		properties.setProperty("offsetAsPageNum", "true");
		properties.setProperty("rowBoundsWithCount", "true");
		properties.setProperty("reasonable", "true");
		pageHelper.setProperties(properties);
		return pageHelper;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new PjaxInterceptor());
	}
}
