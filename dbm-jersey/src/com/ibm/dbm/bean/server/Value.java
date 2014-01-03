package com.ibm.dbm.bean.server;

public class Value {

	private Object value;
	private String unit;

	public Value() {
		
	}
	
	public Value(Object value, String unit) {
		this.value = value;
		this.unit = unit;
	}
	
	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

}
