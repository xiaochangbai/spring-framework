package com.xiaochangbai.spring.context.service.impl;

import com.xiaochangbai.spring.context.service.IUserService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("singleton")
public class UserServiceImpl implements IUserService {
	public UserServiceImpl() {
		System.out.println(this.getClass().getSimpleName() + " 构造函数执行...");
	}
}
