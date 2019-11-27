package com.sebt.model;

public class Data {
	
	private Integer index;
	
	private String value;
	
	private Integer frequence;
	
	private Integer output;
	
	public Data(Integer index, String value, Integer frequence, Integer output) {
		super();
		this.index = index;
		this.value = value;
		this.frequence = frequence;
		this.output = output;
	}

	
	public Data() {
		
	}
	

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Integer getFrequence() {
		return frequence;
	}

	public void setFrequence(Integer frequence) {
		this.frequence = frequence;
	}

	public Integer getOutput() {
		return output;
	}

	public void setOutput(Integer output) {
		this.output = output;
	}

}
