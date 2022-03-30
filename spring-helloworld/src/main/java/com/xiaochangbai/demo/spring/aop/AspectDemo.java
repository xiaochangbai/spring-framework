package com.xiaochangbai.demo.spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectDemo {

	@Around(value = "execution(* com.xiaochangbai.demo.spring.service.*.*(..))")
	public Object around(ProceedingJoinPoint point) throws Throwable {
		System.out.println("before run ..................");
		Object result = point.proceed();
		System.out.println("after run ..................");
		return result;
	}

}
