package com.meedesidy.jeedey.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Init {
	public static String path = System.getProperty("user.dir") + "/src/main/java/com/meedesidy/jeedey/controllers/";
	
	public static String filenameTemp;
	
	public static void main(String[] args) {
		createFile("RolesController", "sssssssssssssssssssssssssssss");
		
	}
	
	public static boolean createFile(String fileName, String filecontent){
		Boolean bool = false;
		filenameTemp = path + fileName + ".java";
		File file = new File(filenameTemp);
		System.out.println("文件");
		try {
			System.out.println("准备创建文件" + file.getPath());
			file.createNewFile();
//			if(!file.exists()) {
				bool = true;
				
				System.out.println("create new file : " + filenameTemp);
				
				writeFileContent(filenameTemp, filecontent);
//			}
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
}
