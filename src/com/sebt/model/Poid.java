package com.sebt.model;

import java.util.ArrayList;
import java.util.List;

public class Poid {
	
	/*
	 * VARIABLE
	 */
	private Double value;

	private List<Integer> exposant;

	/*
	 * INITIALIZE
	 */
	public Poid() {
		this.exposant = new ArrayList<>(1);
		this.value = 0.0;
	}
	
	public Poid(Double value,List<Integer> exposant) {
		this.exposant = exposant;
		this.value = value;
	}
	
	/*
	 * GETTER & SETTER
	 */
	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public List<Integer> getExposant() {
		return exposant;
	}

	public void setExposant(List<Integer> exposant) {
		this.exposant = exposant;
	}
	
}
