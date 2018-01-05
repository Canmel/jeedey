package com.meedesidy.jeedey.service;

import java.util.List;

import com.meedesidy.jeedey.entity.User;

public interface UserService extends BaseService {

	public User save(User user, List<Integer> roleIds);
}
