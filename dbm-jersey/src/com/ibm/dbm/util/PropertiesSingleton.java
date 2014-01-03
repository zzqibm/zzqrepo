package com.ibm.dbm.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class PropertiesSingleton {
	private static PropertiesSingleton ps = new PropertiesSingleton();
	
	Properties p    = new Properties();
	Properties mail = new Properties();
	
	private PropertiesSingleton(){
		init();
	}
	
	public static PropertiesSingleton getInstance(){
		return ps;
	}
	
	public void init(){
		InputStream in  = this.getClass().getClassLoader().getResourceAsStream("sysparam.properties");
		InputStream min = this.getClass().getClassLoader().getResourceAsStream("mail.properties");
		try {
			p.load(in);
			mail.load(min);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String logToDir = p.getProperty(Constants.LOG_TO_DIR);
		File dirFile = new File(logToDir);
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }
	}
	
	public Properties getMailProperties(){
		return mail;
	}
	
	public String getPropertyValue(String name){
		return p.getProperty(name);
	}
}
