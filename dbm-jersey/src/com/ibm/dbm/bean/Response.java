package com.ibm.dbm.bean;

public class Response {
	
	public Status status;
	private String message;
	private Object content;
	
	public Response() {
		
	}
	
	public Response(Status status, String message, Object content) {
		this.status = status;
		this.message = message;
		this.content = content;
	}
	
	public Status getStatus() {
		return status;
	}
	
	public void setStatus(Status status) {
		this.status = status;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public Object getContent() {
		return content;
	}
	
	public void setContent(Object content) {
		this.content = content;
	}
	
	public enum Status {
		Successful,
		Failed
	}
}
