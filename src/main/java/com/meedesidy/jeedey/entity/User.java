package com.meedesidy.jeedey.entity;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;

public class User extends BaseEntity{

	@Email
	private String email;
	
	@Pattern(regexp="^1[3|4|5|8][0-9]\\d{4,8}$", message="请输入正确的手机号码")
	private String phone;

	public User(String name, String email, String phone) {
		super();
		super.setName(name);
		this.email = email;
		this.phone = phone;
	}
	
	public User() {
	}

	public User(int id) {
		super(id);
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}
