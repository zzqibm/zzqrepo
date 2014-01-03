package com.ibm.dbm.bean.server;

public class HbaCard extends Card {

	private String wwpn;

	public HbaCard() {
		
	}
	
	public HbaCard(String index, String name, String description, String lparId, String lparName, CardType type, CardStatus status, String wwpn) {
		this.index = index;
		this.name = name;
		this.description = description;
		this.lparId = lparId;
		this.lparName = lparName;
		this.type = type;
		this.status = status;
		this.wwpn = wwpn;
	}
	
	public String getWwpn() {
		return wwpn;
	}

	public void setWwpn(String wwpn) {
		this.wwpn = wwpn;
	}

}
