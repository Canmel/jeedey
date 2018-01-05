package com.meedesidy.jeedey.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meedesidy.jeedey.entity.User;
import com.meedesidy.jeedey.mapper.UserMapper;
import com.meedesidy.jeedey.service.SessionService;

@Service(value = "sessionService")
public class SessionServiceImpl extends BaseServiceImpl implements SessionService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public UserMapper getMapper() {
		return userMapper;
	}

	@Override
	public List<User> doLogin(User user) throws Exception {
		return userMapper.loginByEmailAndPassword(user);
	}

}
