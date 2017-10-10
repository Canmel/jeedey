package com.meedesidy.jeedey.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meedesidy.jeedey.mapper.MenuMapper;
import com.meedesidy.jeedey.service.MenuService;

@Service(value = "menuService")
public class MenuServiceImpl extends BaseServiceImpl implements	MenuService{

	@Autowired
	private MenuMapper menuMapper;
	
	@Override
	public MenuMapper getMapper() {
		return menuMapper;
	}
}
