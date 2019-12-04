package com.sebt.model;

import java.util.ArrayList;
import java.util.List;

public class DataFile {
	
	private static RegExIndex reg = new RegExIndex();
	
	private List<RegExIndex> listRegEx = new ArrayList<>();
	
	private Integer output;
	
//	public DataFile() {
//		
//		this.listRegEx = reg.getCloneList();
//	
//	}
//	
//	public DataFile(Integer output) {
//		this.listRegEx = reg.getCloneList();
//		this.output = output;
//	}

	public DataFile(String input ,Integer output) {
		this.listRegEx = reg.getCloneList();
		this.output = output;
		// parse input on listRegEx
		for(Character c : input.toCharArray()) {
			addOccurenceForChar(c);
		}
		
		
	}
	
	
	/*
	 * GETTER & SETTER
	 */
	
	private void addOccurenceForChar(Character chara) {
		int index = reg.getIndex(chara);
		if(index != -1) {
			this.listRegEx.get(index).addOccurence();
		}
	}
	
	public List<RegExIndex> getListRegEx() {
		return listRegEx;
	}
	
	public List<Double> getOnlyOccurence(){
		List<Double> list = new ArrayList<>();
		Double somme = 0.0;
		int index = 1;
		for(RegExIndex reg : this.listRegEx) {
//			list.add((double)reg.getNbOcurrence());
			somme += (double)reg.getNbOcurrence() * (reg.getIndex()*index);
			index++;
		}
		list.add(somme);
		return list;
	}
	
	
	public Integer getOutput() {
		return output;
	}

}
