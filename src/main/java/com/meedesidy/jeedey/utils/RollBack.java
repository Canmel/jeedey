package com.meedesidy.jeedey.utils;

import java.io.File;

public class RollBack {
	
	public static final String modelName = "role";
	public static final String path = System.getProperty("user.dir") + "/src/main/java/com/meedesidy/jeedey/controllers/";
	public static final String path_service = System.getProperty("user.dir") + "/src/main/java/com/meedesidy/jeedey/service/";
	public static final String path_entity = System.getProperty("user.dir") + "/src/main/java/com/meedesidy/jeedey/entity/";
	public static final String path_service_impl = System.getProperty("user.dir") + "/src/main/java/com/meedesidy/jeedey/service/impl/";
	public static final String controller_suf = "sController.java";
	public static final String service_suf = "Service.java";
	public static final String service_impl_suf = "ServiceImpl.java";
	public static final String entity_suf = ".java";
	
	public static void main(String[] args) {
		deleteFiles();
	}

	public static boolean deleteFile(String filePath) {
		boolean isDelete = false;
		System.out.println("准备删除 : " + filePath);
		File tempFile = new File(filePath);
		if(tempFile.exists()) {
			isDelete = tempFile.delete();
		}
		System.out.println(isDelete ? "已删除" : "未找到文件");
		return isDelete;
	}
	
	public static void deleteFiles() {
		
		deleteFile(path + Init.firstUpperCase(modelName) + controller_suf);
		
		deleteFile(path_service + Init.firstUpperCase(modelName) + service_suf);
		
		deleteFile(path_entity + Init.firstUpperCase(modelName) + entity_suf);
		
		deleteFile(path_service_impl + Init.firstUpperCase(modelName) + service_impl_suf);
	}
}
