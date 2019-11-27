package com.sebt.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sebt.constante.Constante;
import com.sebt.model.Data;
import com.sebt.utils.ReadFileToConvert;
import com.sebt.utils.WriteFile;

public class PreparedData {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("Get Value of file best use");
		try {
			Map<String,Integer> valueBad = ReadFileToConvert.getUseRegExBadQuery();
			Map<String,Integer> valueGood = ReadFileToConvert.getUseRegExGoodQuery();
			Map<String,Data> joinValue = compareToValueReg(valueBad,valueGood);
			WriteFile.saveDataTraining(joinValue, "test.txt");
//			WriteFile.saveUseRegInTrainingExemple(ReadFileToConvert.getUseRegExBadQuery(), Constante.FILE_SAVE_USE_BAD_QUERY);
//			WriteFile.saveUseRegInTrainingExemple(ReadFileToConvert.getUseRegExGoodQuery(), Constante.FILE_SAVE_USE_GOOD_QUERY);
//			System.out.println("file for bad query  : "+Constante.FILE_SAVE_USE_BAD_QUERY + " as create.");
//			System.out.println("file for good query : "+Constante.FILE_SAVE_USE_GOOD_QUERY + " as create.");
			System.out.println("End write file");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
	}
	
	public static Map<String,Data> compareToValueReg(Map<String,Integer> valueBad, Map<String,Integer> valueGood) {

		Map<String,Data> listData = new HashMap<>();
		Integer index = 0;
		for(Map.Entry<String, Integer> entry : valueBad.entrySet()) {
			listData.put(entry.getKey(),new Data(index,entry.getKey(),entry.getValue(),0));
			index++;
		}
		
		for(Map.Entry<String, Integer> entry : valueGood.entrySet()) {

			if(listData.containsKey(entry.getKey())) {
				if(listData.get(entry.getKey()).getFrequence() < entry.getValue()) {
					listData.replace(entry.getKey(),new Data(index,entry.getKey(),entry.getValue(),1));
					index++;
				}
			}else {
				listData.put(entry.getKey(),new Data(index,entry.getKey(),entry.getValue(),1));
				index++;
			}
			
			
		}
		
		
		return listData;
	}

}
