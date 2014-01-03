package com.ibm.dbm.bean.server;

public class Card {
	protected String index;
	protected String name;
	protected String description;
	protected String lparId;
	protected String lparName;
	protected CardType type;
	protected CardStatus status;

	public Card() {
		
	}
	
	public Card(String index, String name, String description, String lparId, String lparName, CardType type, CardStatus status) {
		this.index = index;
		this.name = name;
		this.description = description;
		this.lparId = lparId;
		this.lparName = lparName;
		this.type = type;
		this.status = status;
	}

	public CardStatus getStatus() {
		return status;
	}

	public void setStatus(CardStatus status) {
		this.status = status;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLparId() {
		return lparId;
	}

	public void setLparId(String lparId) {
		this.lparId = lparId;
	}

	public String getLparName() {
		return lparName;
	}

	public void setLparName(String lparName) {
		this.lparName = lparName;
	}

	public CardType getType() {
		return type;
	}

	public void setType(CardType type) {
		this.type = type;
	}

}
