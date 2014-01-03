package com.ibm.dbm.init;

import com.ibm.dbm.util.Constants;
import com.ibm.dbm.util.PropertiesSingleton;
import com.ibm.dbm.util.RemoteShellInvoke;

public class ShellThread extends Thread{
	
	private String command;
	private Operation operation;
	
	PropertiesSingleton ps = PropertiesSingleton.getInstance();
	String logFormDir = ps.getPropertyValue(Constants.LOG_FROM_DIR);
	
	public ShellThread(String command, Operation op){
		this.command = command;
		this.operation = op;
	}
	
	public void run(){
		System.out.println("thread start: " + this.command);
		RemoteShellInvoke rsi = new RemoteShellInvoke();
		rsi.setCommand(command + " >& "+ logFormDir + "_"+ operation);
		try {
			int returnCode = rsi.exec();
			System.out.println("call shell end: requestid-" + this.command);
			System.out.println("return Code:" + returnCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
