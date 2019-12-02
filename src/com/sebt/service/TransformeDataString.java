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
	
	
	public static Dataset transformeToDataSetForUsingInRegressionLogistique(Map<String,Integer> allValue) {
		
		
		return null;
	}
	
	
}
