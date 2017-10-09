package com.meedesidy.jeedey.entity;

import com.meedesidy.jeedey.entity.enums.Status;

public class User extends BaseEntity{

	private String email;
	
	private String phone;
	
	private Status status;

	public User(String name, String email, String phone) {
		super();
		super.setName(name);
		this.email = email;
		this.phone = phone;
		this.status = Status.active;
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

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
}
