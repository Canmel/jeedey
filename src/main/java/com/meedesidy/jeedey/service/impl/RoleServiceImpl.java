package com.meedesidy.jeedey.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meedesidy.jeedey.entity.Role;
import com.meedesidy.jeedey.mapper.RoleMapper;
import com.meedesidy.jeedey.service.RoleService;

@Service(value = "roleService")
public class RoleServiceImpl extends BaseServiceImpl implements	RoleService{

	@Autowired
	private RoleMapper roleMapper;
	
	@Override
	public RoleMapper getMapper() {
		return roleMapper;
	}

	@Override
	public List<Role> all(Role role) {
		// TODO Auto-generated method stub
		return null;
	}
}
