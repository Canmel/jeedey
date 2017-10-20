package com.meedesidy.jeedey.service;

import java.util.List;

import com.meedesidy.jeedey.entity.Menu;

public interface MenuService extends BaseService {

	public List<Menu> getTopMenus();
}
