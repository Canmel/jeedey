package com.meedesidy.jeedey.entity;

public class Role extends BaseEntity{
	private String desc;

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Role(Integer id) {
		super(id);
	}
	
	public Role() {
	}
}