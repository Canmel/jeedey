package com.meedesidy.jeedey.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

public class Init {
	public static String path = System.getProperty("user.dir") + "/src/main/java/com/meedesidy/jeedey/controllers/";
	public static String path_service = System.getProperty("user.dir") + "/src/main/java/com/meedesidy/jeedey/service/";
	public static String path_entity = System.getProperty("user.dir") + "/src/main/java/com/meedesidy/jeedey/entity/";
	public static String path_service_impl = System.getProperty("user.dir")
			+ "/src/main/java/com/meedesidy/jeedey/service/impl/";
	public static String filenameTemp;

	public static void main(String[] args) throws FileNotFoundException {
		String modelName = "menu";
		System.out.println(StringUtils.capitalize(modelName));
		System.out.println(File.separator);
		createFile(StringUtils.capitalize(modelName) + "sController", getControllerStr(modelName), path); // Controller
		createFile(StringUtils.capitalize(modelName) + "Service", getServiceStr(modelName), path_service); // Service
		createFile(StringUtils.capitalize(modelName), getEntityStr(modelName), path_entity); // Entity
	}

	/**
	 * 创建文件
	 * 
	 * @param fileName
	 * @param fileContent
	 * @param path
	 * @return
	 */
	public static boolean createFile(String fileName, String fileContent, String path) {
		Boolean bool = false;
		filenameTemp = path + fileName + ".java";
		File file = new File(filenameTemp);
		System.out.println("文件");
		try {
			System.out.println("准备创建文件" + file.getPath());
			FileUtils.writeStringToFile(file, fileContent, "utf-8");
			System.out.println("create new file : " + filenameTemp);
			bool = true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(bool);
		return bool;
	}

	/**
	 * Copy Controller
	 * 
	 * @param modelName
	 * @return
	 * @throws FileNotFoundException
	 */
	public static String getControllerStr(String modelName) throws FileNotFoundException {
		File fileout = new File(System.getProperty("user.dir")
				+ "/src/main/java/com/meedesidy/jeedey/controllers/UsersController.java");
		FileInputStream outFis = new FileInputStream(fileout);
		String outcontent = "";
		try {
			byte[] tempbytes = new byte[10000];
			// 字节设置大一点 一次读完
			while ((outFis.read(tempbytes)) != -1) {
				outcontent = new String(tempbytes);
			}
			// 准备替换User
			System.out
					.println("=======================================================================================");
			outcontent = outcontent.replace("User", firstUpperCase(modelName));
			outcontent = outcontent.replace("user", modelName);

			System.out.println(outcontent);
		} catch (Exception e) {
			// TODO: handle exception
		}

		return outcontent;
	}

	/**
	 * Copy Service
	 * 
	 * @param modelName
	 * @return
	 * @throws FileNotFoundException
	 */
	public static String getServiceStr(String modelName) throws FileNotFoundException {
		File fileout = new File(
				System.getProperty("user.dir") + "/src/main/java/com/meedesidy/jeedey/service/UserService.java");
		FileInputStream outFis = new FileInputStream(fileout);
		String outcontent = "";
		try {
			byte[] tempbytes = new byte[10000];
			// 字节设置大一点 一次读完
			while ((outFis.read(tempbytes)) != -1) {
				outcontent = new String(tempbytes);
			}
			// 准备替换User
			System.out
					.println("=======================================================================================");
			outcontent = outcontent.replace("User", firstUpperCase(modelName));
			outcontent = outcontent.replace("user", modelName);

			System.out.println(outcontent);
		} catch (Exception e) {
			// TODO: handle exception
		}

		return outcontent;
	}

	/**
	 * copy Entity
	 * 
	 * @param modelName
	 * @return
	 * @throws FileNotFoundException
	 */
	public static String getEntityStr(String modelName) throws FileNotFoundException {
		String strTemp = entityModel();
		strTemp = strTemp.replace("User", firstUpperCase(modelName));
		strTemp = strTemp.replace("user", modelName);
		return strTemp;
	}

	public static String firstUpperCase(String str) {
		char[] strs = str.toCharArray();
		strs[0] -= 32;
		return String.valueOf(strs);
	}

	public static String entityModel() {
		String str = "package com.meedesidy.jeedey.entity;\n" + "\n"
				+ "import com.meedesidy.jeedey.entity.enums.Status;\n" + "\n" + "public class User extends BaseEntity{"
				+ "\n" + "	private Status status;" + "\n" + "		public Status getStatus() {\n"
				+ "		return status;\n" + "	}\n" + "\n" + "	public void setStatus(Status status) {\n"
				+ "		this.status = status;\n" + "	}" + "\n" + "}";
		return str;
	}

}
