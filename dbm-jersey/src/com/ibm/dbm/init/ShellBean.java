package com.ibm.dbm.init;

public class ShellBean {
	
	private String command;
	private Operation operation;
	
	public ShellBean() {
		
	}
	
	public ShellBean(String command, Operation operation) {
		this.command = command;
		this.operation = operation;
	}
	
	public String getCommand() {
		return command;
	}
	
	public void setCommand(String command) {
		this.command = command;
	}
	
	public Operation getOperation() {
		return operation;
	}
	
	public void setOperation(Operation operation) {
		this.operation = operation;
	}
}
