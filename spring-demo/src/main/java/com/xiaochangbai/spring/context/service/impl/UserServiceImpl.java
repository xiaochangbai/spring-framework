package com.xiaochangbai.spring.context.service.impl;

import com.xiaochangbai.spring.context.service.IUserService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("singleton")
public class UserServiceImpl implements IUserService {

	@Override
	public String sayHello(String name) {
		return String.format("Hello,%s",name);
	}

}
