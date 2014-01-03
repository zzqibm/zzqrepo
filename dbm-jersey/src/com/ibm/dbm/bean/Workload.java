package com.ibm.dbm.bean;

import java.util.List;

import com.ibm.dbm.bean.storage.StoragePool;

public class Workload {

	private String serverName;
	private StoragePool svcPool; // properties "svc_ip" and "name" will be used
									// here.
	private String imageName;

	private int id;
	private String name;
	private double physicalCPU;
	private int virtualCPU;
	private double memory;
	private double disk;
	private Network network;
	private List<String> ethCards;
	private List<String> hbaCards;

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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public List<String> getEthCards() {
		return ethCards;
	}

	public void setEthCards(List<String> ethCards) {
		this.ethCards = ethCards;
	}

	public List<String> getHbaCards() {
		return hbaCards;
	}

	public void setHbaCards(List<String> hbaCards) {
		this.hbaCards = hbaCards;
	}

}
