package com.meedesidy.jeedey.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;

@EnableAutoConfiguration
@Configuration
@MapperScan("com.meedesidy.jeedey.mapper")
public class MyBatisConfig {

}
