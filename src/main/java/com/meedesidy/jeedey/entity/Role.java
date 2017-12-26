package com.meedesidy.jeedey.entity;

import org.hibernate.validator.constraints.Length;

public class Role extends BaseEntity{
	
	@Length(max = 20, message = "角色备注长度不超过20个字符")
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