package com.meedesidy.jeedey.config;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class AopConfig {

	@Pointcut("execution(* com.meedesidy.jeedey.service.impl.*Impl.pageQuery(..))")
	public void excudeService(){}
	
	@Before("excudeService()")
	public void before() {
		System.out.println("切面before执行了 ");
	}
	
	@After("excudeService()")
	public void after() {
		System.out.println("切面after执行了 ");
	}
	
	@AfterReturning("excudeService()")
    public void afterReturning() {
        System.out.println("切面afterReturning执行了");
    }
	
	@AfterThrowing("excudeService()")
    public void afterThrowing() {
        System.out.println("切面afterThrowing执行了");
    }
	
}
