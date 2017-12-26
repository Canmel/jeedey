package com.meedesidy.jeedey.entity;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.Length;

public class Menu extends BaseEntity{
	
	private Menu top;

	@Max(value = 2, message = "菜单级别为1或2")
	@Min(value = 1, message = "菜单级别为1或2")
	private Integer level;
	
	@Length(max = 20, message = "菜单备注长度不超过20个字符")
	private String desc;
	
	@Length(max = 50, min = 1, message = "菜单地址长度为1-50个字符")
	private String resource;

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public Menu getTop() {
		return top;
	}

	public void setTop(Menu top) {
		this.top = top;
	}

	public Menu(Integer id, Menu top, int level, String resource) {
		super(id);
		this.top = top;
		this.level = level;
		this.resource = resource;
	}

	public Menu(Integer id) {
		super(id);
	}
	
	public Menu() {
		// TODO Auto-generated constructor stub
	}
}