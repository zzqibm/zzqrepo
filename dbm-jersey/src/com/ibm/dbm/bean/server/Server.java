package com.ibm.dbm.bean.server;

import java.util.List;

public class Server {

	private int id;
	private String name;
	private String hmcUser;
	private String hmcIp;

	private Value availableCPU;
	private Value availableMemory;
	
	private List<Card> ethernetCards;
	private List<Card> hbaCards;

	public Value getAvailableCPU() {
		return availableCPU;
	}

	public void setAvailableCPU(Value availableCPU) {
		this.availableCPU = availableCPU;
	}

	public Value getAvailableMemory() {
		return availableMemory;
	}

	public void setAvailableMemory(Value availableMemory) {
		this.availableMemory = availableMemory;
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

	public String getHmcUser() {
		return hmcUser;
	}

	public void setHmcUser(String hmcUser) {
		this.hmcUser = hmcUser;
	}

	public String getHmcIp() {
		return hmcIp;
	}

	public void setHmcIp(String hmcIp) {
		this.hmcIp = hmcIp;
	}
	
	public List<Card> getEthernetCards() {
		return this.ethernetCards;
	}

	public List<Card> getHbaCards() {
		return hbaCards;
	}

	public void setHbaCards(List<Card> hbaCards) {
		this.hbaCards = hbaCards;
	}

	public void setEthernetCards(List<Card> ethernetCards) {
		this.ethernetCards = ethernetCards;
	}

}
