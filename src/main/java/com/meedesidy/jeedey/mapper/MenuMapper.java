package com.meedesidy.jeedey.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.meedesidy.jeedey.entity.Menu;


@Mapper
public interface MenuMapper extends BaseMapper{
	public List<Menu> getTopMenus();
}
