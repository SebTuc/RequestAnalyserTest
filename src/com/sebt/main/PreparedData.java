package com.sebt.main;

import java.util.Map;

import com.sebt.model.Data;
import com.sebt.service.TransformeDataString;
import com.sebt.utils.ReadFileToConvert;
import com.sebt.utils.WriteFile;

public class PreparedData {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("Get Value of file best use");
		try {
			Map<String,Integer> valueBad = ReadFileToConvert.getUseRegExBadQuery();
			Map<String,Integer> valueGood = ReadFileToConvert.getUseRegExGoodQuery();
			Map<String,Data> joinValue = TransformeDataString.compareToValueReg(valueBad,valueGood);
			WriteFile.saveDataTraining(joinValue, "test1.txt");
			
//			WriteFile.saveUseRegInTrainingExemple(TransformeDataString.generateDictionnary(),"testDico.txt");
			System.out.println("End write file");
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
		try {
//			Map<Double,Integer> valueBad = ReadFileToConvert.getFile(Constante.PATH_FILE+Constante.FILE_BAD_QUERY,1);
//			Map<Double,Integer> valueGood = ReadFileToConvert.getFile(Constante.PATH_FILE+Constante.FILE_GOOD_QUERY,0);
//			Map<Double,Integer> allValue = new HashMap<>();
//			allValue.putAll(valueGood);
//			allValue.putAll(valueBad);
			
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
