package com.meedesidy.jeedey.entity.enums;

import java.util.ArrayList;
import java.util.List;

public class Levels extends BaseEnum {
	
	public Levels() {
	}
	
	public Levels(int ind, String code, String name) {
		super(ind, code, name);
	}
	
	public static Levels top_level = new Levels(1, "top_level", "一级菜单");
	public static Levels sub_level = new Levels(0, "sub_level", "二级菜单");
	
	public static List<Levels> levelList(){
		List<Levels> levels = new ArrayList<Levels>();
		levels.add(sub_level);
		levels.add(top_level);
		return levels;
	}
}
