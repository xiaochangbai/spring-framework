package com.xiaochangbai.demo.spring.service;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements ApplicationContextAware {

	public void sayHi(){
		System.out.println("Hello Spring！");
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

	}
}