package com.sebt.main;

import java.util.HashMap;
import java.util.Map;

import com.sebt.constante.Constante;
import com.sebt.model.Dataset;
import com.sebt.model.ModelRegression;
import com.sebt.service.RegressionLogistiqueV2;
import com.sebt.utils.ReadFileToConvert;
import com.sebt.utils.WriteFile;

public class RunningLearn {

//	private static RegressionLogistiqueService regService = new RegressionLogistiqueService();
	
	private static RegressionLogistiqueV2 regService = new RegressionLogistiqueV2();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			
//    		Map<Double,Integer> allContainsFile= new HashMap<>();
			Map<String,Integer> allContainsFile = new HashMap<>();
//    		Map<String,Integer> valueBad = ReadFileToConvert.getUseRegExBadQuery();
//			Map<String,Integer> valueGood = ReadFileToConvert.getUseRegExGoodQuery();
//			Map<String,Data> joinValue = TransformeDataString.compareToValueReg(valueBad,valueGood);
//    		allContainsFile.putAll(ReadFileToConvert.getFile(Constante.PATH_FILE+Constante.FILE_BAD_QUERY,1));
//    		allContainsFile.putAll(ReadFileToConvert.getFile(Constante.PATH_FILE+Constante.FILE_GOOD_QUERY,0));
    		System.out.println("Read bad Query");
    		allContainsFile.putAll(ReadFileToConvert.getFile(Constante.PATH_FILE+Constante.FILE_BAD_QUERY,1));
    		System.out.println("end read bad Query");
    		System.out.println("Read good Query");
    		allContainsFile.putAll(ReadFileToConvert.getFile(Constante.PATH_FILE+Constante.FILE_GOOD_QUERY,0));
    		System.out.println("end read good Query");
			
    		Dataset dataset = new Dataset(allContainsFile);
    		
    		System.out.println("-----------------------------------------------------------------------");
    		System.out.println("Nb data for all query : " + allContainsFile.size());
    		System.out.println("-----------------------------------------------------------------------");
    		
    		System.out.println("------  train model");
//			ModelRegression bestModel = regService.TrainAndFindBestModel(allContainsFile);
    		ModelRegression bestModel = regService.TrainAndFindBestModel(dataset);
    		
			System.out.println("------  end train model");
			
			System.out.println("-----------------------------------------------------------------------");
			
			System.out.println("////////////////////   Precision : " +  bestModel.getPrecision());
			System.out.println("////////////////////   Recall    : " +  bestModel.getRecall());
			System.out.println("////////////////////   F1Score   : " +  bestModel.getF1Score());
			
			System.out.println("-----------------------------------------------------------------------");
			
			System.out.println("Write model :");
//			Constante.BEST_MODEL = bestModel;
			WriteFile.saveModelLearn(bestModel);
			
			System.out.println("End write");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
	}

}
