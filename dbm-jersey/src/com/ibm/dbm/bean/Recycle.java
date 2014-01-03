package com.ibm.dbm.bean;

public class Recycle {

	private String partitionName;
	private String sourceServerName;
	private String targetServerName;
	private String ip;
	private String storageIp;

	public String getPartitionName() {
		return partitionName;
	}

	public void setPartitionName(String partitionName) {
		this.partitionName = partitionName;
	}

	public String getSourceServerName() {
		return sourceServerName;
	}

	public void setSourceServerName(String sourceServerName) {
		this.sourceServerName = sourceServerName;
	}

	public String getTargetServerName() {
		return targetServerName;
	}

	public void setTargetServerName(String targetServerName) {
		this.targetServerName = targetServerName;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getStorageIp() {
		return storageIp;
	}

	public void setStorageIp(String storageIp) {
		this.storageIp = storageIp;
	}

}
