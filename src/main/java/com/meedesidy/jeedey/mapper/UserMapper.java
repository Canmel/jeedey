package com.meedesidy.jeedey.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.meedesidy.jeedey.entity.BaseEntity;
import com.meedesidy.jeedey.entity.User;


@Mapper
public interface UserMapper extends BaseMapper{
	
	public List<User> queryAll(BaseEntity entity);
	
	public List<User> loginByEmailAndPassword(User user);
}
