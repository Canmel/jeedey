package com.meedesidy.jeedey.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class Init {
	public static String path = System.getProperty("user.dir") + "/src/main/java/com/meedesidy/jeedey/controllers/";
	public static String path_service = System.getProperty("user.dir") + "/src/main/java/com/meedesidy/jeedey/service/";
	public static String path_entity = System.getProperty("user.dir") + "/src/main/java/com/meedesidy/jeedey/entity/";
	public static String path_service_impl = System.getProperty("user.dir") + "/src/main/java/com/meedesidy/jeedey/service/impl/";
	public static String filenameTemp;
	
	public static void main(String[] args) throws FileNotFoundException {
		String modelName = "role";
		createFile(firstUpperCase(modelName) + "sController", getControllerStr(modelName), path); 
		createFile(firstUpperCase(modelName) +  "Service", getServiceStr(modelName), path_service);
		createFile(firstUpperCase(modelName), getEntityStr(modelName), path_entity);
	}
	
	public static boolean createFile(String fileName, String fileContent, String path){
		Boolean bool = false;
		filenameTemp = path + fileName + ".java";
		File file = new File(filenameTemp);
		System.out.println("文件");
		try {
			System.out.println("准备创建文件" + file.getPath());
			file.createNewFile();
			bool = true;
			System.out.println("create new file : " + filenameTemp);
			writeFileContent(filenameTemp, fileContent);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(bool);
		return bool;
	}
	
	public static boolean writeFileContent(String filepath, String newStr) throws IOException{
		Boolean bool = false;
		String filein = newStr + "\r\n";
		String temp = "";
		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		FileOutputStream fos = null;
		PrintWriter pw = null;
		try {
			File file = new File(filepath);
			fis = new FileInputStream(file);
			isr = new InputStreamReader(fis);
			br = new BufferedReader(isr);
			StringBuffer buffer = new StringBuffer();
			
			for (int i = 0; (temp = br.readLine()) != null; i++) {
				buffer.append(temp);
				buffer = buffer.append(System.getProperty("line.separator"));
			}
			buffer.append(filein);
			fos = new FileOutputStream(file);
			pw = new PrintWriter(fos);
			pw.write(buffer.toString().toCharArray());
			pw.flush();
			bool = true;
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			if(pw != null) {
				pw.close();
			}
			if(fos != null) {
				fos.close();
			}
			if(br != null) {
				pw.close();
			}
			if(isr != null) {
				pw.close();
			}
		}
		
		
		return bool;
	}
	
	public static String getControllerStr(String modelName) throws FileNotFoundException {
		File fileout = new File(System.getProperty("user.dir") + "/src/main/java/com/meedesidy/jeedey/controllers/UsersController.java");
		FileInputStream outFis = new FileInputStream(fileout);
		String outcontent = "";
		try {
			byte[] tempbytes = new byte[10000];
			//字节设置大一点 一次读完
            while ((outFis.read(tempbytes)) != -1) {
                outcontent = new String(tempbytes);
            }
          //准备替换User
    		System.out.println("22=======================================================================================");
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
	 * @param modelName
	 * @return
	 * @throws FileNotFoundException
	 */
	public static String getServiceStr(String modelName) throws FileNotFoundException {
		File fileout = new File(System.getProperty("user.dir") + "/src/main/java/com/meedesidy/jeedey/service/UserService.java");
		FileInputStream outFis = new FileInputStream(fileout);
		String outcontent = "";
		try {
			byte[] tempbytes = new byte[10000];
			//字节设置大一点 一次读完
            while ((outFis.read(tempbytes)) != -1) {
                outcontent = new String(tempbytes);
            }
          //准备替换User
    		System.out.println("22=======================================================================================");
    		outcontent = outcontent.replace("User", firstUpperCase(modelName));
    		outcontent = outcontent.replace("user", modelName);
    		
    		System.out.println(outcontent);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return outcontent;
	}
	
	public static String getEntityStr(String modelName) throws FileNotFoundException {
		String strTemp = entityModel();
		strTemp = strTemp.replace("User", firstUpperCase(modelName));
		strTemp = strTemp.replace("user", modelName);
		return strTemp;
	}
	
	
	public static String firstUpperCase(String str) {
		char[] strs = str.toCharArray();
		strs[0]-=32;
		return String.valueOf(strs);
	}
	
	public static String entityModel() {
		String str = "package com.meedesidy.jeedey.entity;\n" + 
				"\n" + 
				"import com.meedesidy.jeedey.entity.enums.Status;\n" + 
				"\n" + 
				"public class User extends BaseEntity{" + 
				"\n" + 
				"}";
		return str;
	}
	
	
}
