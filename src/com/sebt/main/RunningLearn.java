package com.sebt.main;

import java.util.HashMap;
import java.util.Map;

import com.sebt.constante.Constante;
import com.sebt.model.Data;
import com.sebt.model.ModelRegression;
import com.sebt.service.RegressionLogistiqueService;
import com.sebt.service.TransformeDataString;
import com.sebt.utils.ReadFileToConvert;
import com.sebt.utils.WriteFile;

public class RunningLearn {

	private static RegressionLogistiqueService regService = new RegressionLogistiqueService();
	

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			// generate dictionnary
			System.out.println("-- Create dictionnary ");
			Map<String,Integer> valueBad = ReadFileToConvert.getUseRegExBadQuery();
			Map<String,Integer> valueGood = ReadFileToConvert.getUseRegExGoodQuery();
			Map<String,Data> dico = TransformeDataString.compareToValueReg(valueBad,valueGood);
			System.out.println("-- End create ");
			
			
//    		Map<Double,Integer> allContainsFile= new HashMap<>();
			Map<String,Integer> allContainsFile = new HashMap<>();
//    		Map<String,Integer> valueBad = ReadFileToConvert.getUseRegExBadQuery();
//			Map<String,Integer> valueGood = ReadFileToConvert.getUseRegExGoodQuery();
//			Map<String,Data> joinValue = TransformeDataString.compareToValueReg(valueBad,valueGood);
//    		allContainsFile.putAll(ReadFileToConvert.getGoodQuery());
//    		allContainsFile.putAll(ReadFileToConvert.getBadQuery());
			System.out.println("----------- prepared data -----------------");
    		System.out.println("Read bad Query");
    		allContainsFile.putAll(ReadFileToConvert.getFile(Constante.PATH_FILE+Constante.FILE_BAD_QUERY,1));
    		System.out.println("end read bad Query");
    		System.out.println("Read good Query");
    		allContainsFile.putAll(ReadFileToConvert.getFile(Constante.PATH_FILE+Constante.FILE_GOOD_QUERY,0));
    		System.out.println("end read good Query");
    		System.out.println("TransformData ");
    		Map<Double,Integer> dataTransform = TransformeDataString.transformeDataToValue(dico, allContainsFile);
    		WriteFile.saveTransformDataValue(dataTransform);
    		System.out.println("End TransformData ");
    		System.out.println("----------- end prepared data -----------------");
//    		System.out.println("-- TransformeDataObject ");
//    		Map<String,Integer> dictionnary = TransformeDataString.transformListDataObjectToMapOnlyPositif(dico);
//    		System.out.println("-- End TransformeDataObject ");
//    		System.out.println("-- TransformData ");
//    		Map<Double,Integer> dataTransform = TransformeDataString.transformeDataToValue(dictionnary, allContainsFile);
//    		System.out.println("-- End TransformData ");
    		
//    		Dataset dataset = new Dataset(allContainsFile);
    		Double percentagePerte = (((double)allContainsFile.size() - (double)dataTransform.size())/(double)allContainsFile.size())*100.0;
    		System.out.println("-----------------------------------------------------------------------");
    		System.out.println("Nb data for all query : " + allContainsFile.size());
    		System.out.println("Nb data after transform : " + dataTransform.size());
    		System.out.println("Nb same data loose: " + (allContainsFile.size() - dataTransform.size()) +
    				" sois environ : " + percentagePerte + "% de perte");
    		System.out.println("-----------------------------------------------------------------------");
    		
    		System.out.println("------  train model");
			ModelRegression bestModel = regService.TrainAndFindBestModel(dataTransform);
//    		ModelRegression bestModel = regService.TrainAndFindBestModel(dataset);
    		
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
	
	
//	private static RegressionLogistiqueV2 regService = new RegressionLogistiqueV2();
//	public static void main(String[] args) {
//		
//		Map<String,Integer> allContainsFile = new HashMap<>();
//		Map<String,Integer> valueBad = ReadFileToConvert.getUseRegExBadQuery();
//		Map<String,Integer> valueGood = ReadFileToConvert.getUseRegExGoodQuery();
//		Map<String,Data> joinValue = TransformeDataString.compareToValueReg(valueBad,valueGood);
//		allContainsFile.putAll(ReadFileToConvert.getGoodQuery());
//		allContainsFile.putAll(ReadFileToConvert.getBadQuery());
//		System.out.println("-- TransformeDataObject ");
//		Map<String,Integer> dictionnary = TransformeDataString.transformListDataObjectToMapOnlyPositif(dico);
//		System.out.println("-- End TransformeDataObject ");
//		System.out.println("-- TransformData ");
//		Map<Double,Integer> dataTransform = TransformeDataString.transformeDataToValue(dictionnary, allContainsFile);
//		System.out.println("-- End TransformData ");
//		
//		Dataset dataset = new Dataset(allContainsFile);
//		
//		ModelRegression bestModel = regService.TrainAndFindBestModel(dataset);
//	}

}
