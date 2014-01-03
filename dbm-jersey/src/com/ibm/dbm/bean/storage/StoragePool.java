package com.ibm.dbm.bean.storage;

public class StoragePool {

	private String id;
	private String name;
	private String svc_user;
	private String svc_ip;
	private String s_type; // ? TODO
	private String status; // ? TODO
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSvc_user() {
		return svc_user;
	}
	
	public void setSvc_user(String svc_user) {
		this.svc_user = svc_user;
	}
	
	public String getSvc_ip() {
		return svc_ip;
	}
	
	public void setSvc_ip(String svc_ip) {
		this.svc_ip = svc_ip;
	}
	
	public String getS_type() {
		return s_type;
	}
	
	public void setS_type(String s_type) {
		this.s_type = s_type;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
}
