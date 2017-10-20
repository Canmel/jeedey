package com.meedesidy.jeedey.entity;

import com.meedesidy.jeedey.entity.enums.Levels;

public class Menu extends BaseEntity{
	private Menu top;

	private Levels level;
	
	private String resource;
	
	private String desc;

	public Levels getLevel() {
		return level;
	}

	public void setLevel(Levels level) {
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

	public Menu(Integer id, Menu top, Levels level, String resource, String desc) {
		super(id);
		this.top = top;
		this.level = level;
		this.resource = resource;
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Menu(Integer id) {
		super(id);
	}
	
	public Menu() {
		// TODO Auto-generated constructor stub
	}
}