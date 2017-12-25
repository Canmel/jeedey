package com.meedesidy.jeedey.config;

import java.util.Properties;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.pagehelper.PageHelper;

@EnableAutoConfiguration
@Configuration
@MapperScan("com.meedesidy.jeedey.mapper")
public class MyBatisConfig {
	
	@Bean
	public PageHelper pageHelper() {
		PageHelper pageHelper = new PageHelper();
		Properties properties = new Properties();
		properties.setProperty("offsetAsPageNum", "true");
		properties.setProperty("rowBoundsWithCount","true");
		properties.setProperty("reasonable","true");
		properties.setProperty("dialect","mysql"); 
		pageHelper.setProperties(properties);
		return pageHelper;
	}
}
