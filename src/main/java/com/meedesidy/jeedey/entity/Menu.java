package com.meedesidy.jeedey.entity;

public class Menu extends BaseEntity{
	private Menu top;

	private int level;
	
	private String resource;

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
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