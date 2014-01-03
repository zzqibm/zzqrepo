package com.ibm.dbm.bean;

public class Network {

	private String ip;
	private String netmask;
	private String gateway;
	
	public Network() {
		
	}
	
	public Network(String ip, String netmask, String gateway) {
		this.ip = ip;
		this.netmask = netmask;
		this.gateway = gateway;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getNetmask() {
		return netmask;
	}

	public void setNetmask(String netmask) {
		this.netmask = netmask;
	}

	public String getGateway() {
		return gateway;
	}

	public void setGateway(String gateway) {
		this.gateway = gateway;
	}

}
