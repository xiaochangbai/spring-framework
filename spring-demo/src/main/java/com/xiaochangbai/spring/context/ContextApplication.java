package com.xiaochangbai.spring.context;

import com.xiaochangbai.spring.context.config.ContextConfig;
import com.xiaochangbai.spring.context.service.IUserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ContextApplication {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
				ContextConfig.class
		);
		IUserService bean = context.getBean(IUserService.class);
		System.out.println(bean);
	}

}
