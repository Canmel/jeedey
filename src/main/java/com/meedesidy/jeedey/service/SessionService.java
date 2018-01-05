package com.meedesidy.jeedey.service;

import java.util.List;

import com.meedesidy.jeedey.entity.User;

public interface SessionService extends BaseService {

	public List<User> doLogin(User user) throws Exception;
	
}
