package com.ibm.dbm.bean;

import com.ibm.dbm.bean.storage.StoragePool;

public class Deployment {

	public enum Type {
		Logical_Partition,
		Physical_Partition
	}
	
	private String serverName;
	private StoragePool svcPool; // properties "svc_ip" and "name" will be used here.
	private String imageName;

	private Type type;
	
	private String partitionName;
	private double physicalCPU;
	private int virtualCPU;
	private double memory;
	private double disk;
	private Network network;
	
	private String ethCard;
	private String hbaCard;
	private String hbaWwpn;
	
	private int deployNumber; // multi-deployment

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public StoragePool getSvcPool() {
		return svcPool;
	}

	public void setSvcPool(StoragePool svcPool) {
		this.svcPool = svcPool;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getPartitionName() {
		return partitionName;
	}

	public void setPartitionName(String partitionName) {
		this.partitionName = partitionName;
	}

	public double getPhysicalCPU() {
		return physicalCPU;
	}

	public void setPhysicalCPU(double physicalCPU) {
		this.physicalCPU = physicalCPU;
	}

	public int getVirtualCPU() {
		return virtualCPU;
	}

	public void setVirtualCPU(int virtualCPU) {
		this.virtualCPU = virtualCPU;
	}

	public double getMemory() {
		return memory;
	}

	public void setMemory(double memory) {
		this.memory = memory;
	}

	public double getDisk() {
		return disk;
	}

	public void setDisk(double disk) {
		this.disk = disk;
	}

	public Network getNetwork() {
		return network;
	}

	public void setNetwork(Network network) {
		this.network = network;
	}

	public int getDeployNumber() {
		return deployNumber;
	}

	public void setDeployNumber(int deployNumber) {
		this.deployNumber = deployNumber;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public String getEthCard() {
		return ethCard;
	}

	public void setEthCard(String ethCard) {
		this.ethCard = ethCard;
	}

	public String getHbaCard() {
		return hbaCard;
	}

	public void setHbaCard(String hbaCard) {
		this.hbaCard = hbaCard;
	}

	public String getHbaWwpn() {
		return hbaWwpn;
	}

	public void setHbaWwpn(String hbaWwpn) {
		this.hbaWwpn = hbaWwpn;
	}

}
