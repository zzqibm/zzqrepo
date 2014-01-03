package com.ibm.dbm.bean;

import java.util.List;

public class Migration {
	public enum Type {
		V2P, P2V, V2V, P2P
	}

	private String sourceServer;
	private String targetServer;
	private String partitionName;
	private Network network;
	private double physicalCPU;
	private int virtualCPU;
	private double memory;
	private List<String> ethCards;
	private List<String> hbaCards;
	private Type type;

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public String getSourceServer() {
		return sourceServer;
	}

	public void setSourceServer(String sourceServer) {
		this.sourceServer = sourceServer;
	}

	public String getTargetServer() {
		return targetServer;
	}

	public void setTargetServer(String targetServer) {
		this.targetServer = targetServer;
	}

	public String getPartitionName() {
		return partitionName;
	}

	public void setPartitionName(String partitionName) {
		this.partitionName = partitionName;
	}

	public Network getNetwork() {
		return network;
	}

	public void setNetwork(Network network) {
		this.network = network;
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
