package com.meedesidy.jeedey.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meedesidy.jeedey.entity.User;
import com.meedesidy.jeedey.mapper.UserMapper;
import com.meedesidy.jeedey.service.UserService;

@Service(value = "userService")
public class UserServiceImpl extends BaseServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public UserMapper getMapper() {
		return userMapper;
	}

	@Override
	public User save(User user, List<Integer> roleIds) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
