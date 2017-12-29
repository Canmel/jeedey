package com.meedesidy.jeedey.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ValidatorAspect {
	
	@Pointcut("@annotation(com.meedesidy.jeedey.annotation.ModelValidator)")
	private void modelValid() { }

	@Around(value = "modelValid()")
	public Object advice( ProceedingJoinPoint joinPoint) throws Throwable {
		System.out.println("BEFORE TODO1.............");
		
		Object result = null;
		
		try {
			result = joinPoint.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
			throw e;
		}finally {
			System.out.println("AFTER TODO2.............");
		}
		
		return result;
	}
}
