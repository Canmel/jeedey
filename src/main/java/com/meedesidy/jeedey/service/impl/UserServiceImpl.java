package com.meedesidy.jeedey.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meedesidy.jeedey.mapper.UserMapper;
import com.meedesidy.jeedey.service.UserService;

@Service(value = "userService")
public class UserServiceImpl extends BaseServiceImpl implements	UserService{

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public UserMapper getMapper() {
		return userMapper;
	}
}
