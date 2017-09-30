package com.meedesidy.jeedey.entity.enums;

public class Status extends BaseEnum {
	
	public Status() {
	}
	
	public Status(int ind, String code, String name) {
		super(ind, code, name);
	}
	
	public static Status active = new Status(1, "active", "正常");
	public static Status archived = new Status(0, "archived", "删除");
}
