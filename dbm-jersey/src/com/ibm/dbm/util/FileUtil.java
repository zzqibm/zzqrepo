package com.ibm.dbm.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileUtil {
	
	public static String readFileByLines(String fileName) {
		File file = new File(fileName);
		BufferedReader reader = null;
		StringBuffer sb = new StringBuffer();
		try {
			System.out.println("read file by line...");
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			int line = 1;
			// һ�ζ���һ�У�ֱ������nullΪ�ļ�����
			while ((tempString = reader.readLine()) != null){
				sb.append(tempString + "~#@&");
				System.out.println("line " + line + ": " + tempString);
				line++;
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {e1.printStackTrace();}
			}
		}
		return sb.toString();
	}
}
