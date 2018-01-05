package com.meedesidy.jeedey.service;

import java.util.List;

import com.meedesidy.jeedey.entity.Role;

public interface RoleService extends BaseService {
	public List<Role> all(Role role);
}