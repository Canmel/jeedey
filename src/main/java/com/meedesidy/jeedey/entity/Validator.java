package com.meedesidy.jeedey.entity;

import java.util.HashMap;

public class Validator {
	private Boolean isValid;

	private String className;

	private HashMap<String, String> errors;

	public Boolean getIsValid() {
		return isValid;
	}

	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public HashMap<String, String> getErrors() {
		return errors;
	}

	public void setErrors(HashMap<String, String> errors) {
		this.errors = errors;
	}

	public Validator(Boolean isValid, String className, HashMap<String, String> errors) {
		super();
		this.isValid = isValid;
		this.className = className;
		this.errors = errors;
	}

	public Validator() {
		super();
	}
}
