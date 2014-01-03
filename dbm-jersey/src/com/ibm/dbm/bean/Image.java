package com.ibm.dbm.bean;

public class Image {

	private String id; // generated to Database, no use here
	private String name; // only this property is synchronized from Director 
	private String displayName;
	private String status;  // 0:non-exist, 1:exist
	private boolean visible; // show to customer

	// INPUT from GUI, no use here
	private double minCPU;
	private double maxCPU;
	private double minMemory;
	private double maxMemory;
	private double minDiskSize;
	private double maxDiskSize;

	public Image() {
		
	}
	
	public Image(String name) {
		this.name = name;
	}
	
	public Image(String id, String name, String displayName, String status, boolean visible, double minCPU, double maxCPU, double minMemory, double maxMemory, double minDiskSize, double maxDiskSize) {
		this.id = id;
		this.name = name;
		this.displayName = displayName;
		this.status = status;
		this.visible = visible;
		this.minCPU = minCPU;
		this.maxCPU = maxCPU;
		this.minMemory = minMemory;
		this.maxMemory = maxMemory;
		this.minDiskSize = minDiskSize;
		this.maxDiskSize = maxDiskSize;
	}
	
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

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public double getMinCPU() {
		return minCPU;
	}

	public void setMinCPU(double minCPU) {
		this.minCPU = minCPU;
	}

	public double getMaxCPU() {
		return maxCPU;
	}

	public void setMaxCPU(double maxCPU) {
		this.maxCPU = maxCPU;
	}

	public double getMinMemory() {
		return minMemory;
	}

	public void setMinMemory(double minMemory) {
		this.minMemory = minMemory;
	}

	public double getMaxMemory() {
		return maxMemory;
	}

	public void setMaxMemory(double maxMemory) {
		this.maxMemory = maxMemory;
	}

	public double getMinDiskSize() {
		return minDiskSize;
	}

	public void setMinDiskSize(double minDiskSize) {
		this.minDiskSize = minDiskSize;
	}

	public double getMaxDiskSize() {
		return maxDiskSize;
	}

	public void setMaxDiskSize(double maxDiskSize) {
		this.maxDiskSize = maxDiskSize;
	}

}
