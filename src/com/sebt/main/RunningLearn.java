package com.sebt.main;

import java.util.HashMap;
import java.util.Map;

import com.sebt.constante.Constante;
import com.sebt.model.ModelRegression;
import com.sebt.service.RegressionLogistiqueService;
import com.sebt.utils.ReadFileToConvert;
import com.sebt.utils.WriteFile;

public class RunningLearn {

	private static RegressionLogistiqueService regService = new RegressionLogistiqueService();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			
    		Map<Double,Integer> allContainsFile= new HashMap<>();
    		System.out.println("Read bad Query");
    		allContainsFile.putAll(ReadFileToConvert.getBadQuery());
    		System.out.println("end read bad Query");
    		System.out.println("Read good Query");
    		allContainsFile.putAll(ReadFileToConvert.getGoodQuery());
    		System.out.println("end read good Query");
			
    		System.out.println("-----------------------------------------------------------------------");
    		System.out.println("Nb data for all query : " + allContainsFile.size());
    		System.out.println("-----------------------------------------------------------------------");
    		
    		System.out.println("------  train model");
			ModelRegression bestModel = regService.TrainAndFindBestModel(allContainsFile);
    		
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
