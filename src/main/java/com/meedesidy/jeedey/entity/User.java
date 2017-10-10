package com.meedesidy.jeedey.entity;


public class User extends BaseEntity{

	private String email;
	
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
