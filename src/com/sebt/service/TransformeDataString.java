package com.sebt.service;

import java.util.HashMap;
import java.util.Map;

import com.sebt.model.Data;
import com.sebt.model.Dataset;

public class TransformeDataString {
	
	public static Map<String,Data> compareToValueReg(Map<String,Integer> valueBad, Map<String,Integer> valueGood) {

		Map<String,Data> listData = new HashMap<>();
		Integer index = 0;
		for(Map.Entry<String, Integer> entry : valueBad.entrySet()) {
			listData.put(entry.getKey(),new Data(index,entry.getKey(),entry.getValue(),1));
			index++;
		}
		
		for(Map.Entry<String, Integer> entry : valueGood.entrySet()) {

			if(listData.containsKey(entry.getKey())) {
				if(listData.get(entry.getKey()).getFrequence() < entry.getValue()) {
					listData.replace(entry.getKey(),new Data(index,entry.getKey(),entry.getValue(),0));
					index++;
				}
			}else {
				listData.put(entry.getKey(),new Data(index,entry.getKey(),entry.getValue(),0));
				index++;
			}
			
			
		}
		
		
		return listData;
	}
	
	public static Map<String,Integer> transformListDataObjectToMapOnlyPositif(Map<String,Data> dico){
		Map<String,Integer> dictionnary = new HashMap<>();
		for(Map.Entry<String, Data> entry : dico.entrySet()) {
			if(entry.getValue().getOutput() == 1) {
				dictionnary.put(entry.getKey(), entry.getValue().getOutput());
			}
		}
		
		return dictionnary;
		
	}
	
	private static Double getMaxSimilarityPositifValue(String testValue, Map<String,Integer> dictionnary) {
		
		Double maxValue = Double.MIN_VALUE;
		
		for(Map.Entry<String, Integer> entry : dictionnary.entrySet()) {
			if(entry.getValue() == 1) {
				
				Double tempValue = StringSimilarity.similarity(testValue, entry.getKey());
				
				if(tempValue > maxValue) {
					maxValue = tempValue;
				}
			}
			
		}
		if(maxValue == Double.MIN_VALUE) {
			maxValue = 0.0000000000001;
		}
		
		
		return maxValue;
		
	}
		
	public static Map<Double,Integer> transformeDataToValue(Map<String,Integer> dictionnary , Map<String,Integer> allValue) {

		Map<Double,Integer> feature = new HashMap<>();
		
		for(Map.Entry<String, Integer> entry : allValue.entrySet()) {
				
			feature.put(getMaxSimilarityPositifValue(entry.getKey(),dictionnary), entry.getValue());
			
		}
	
		return feature;
		
	}
	
	
}
