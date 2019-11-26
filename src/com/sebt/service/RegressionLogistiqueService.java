package com.sebt.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sebt.constante.Constante;
import com.sebt.model.ModelRegression;
import com.sebt.model.Poid;
import com.sebt.utils.MethodUtils;

public class RegressionLogistiqueService {
	
	/**
	 * 
	 * @param dataSet
	 * @return
	 */
	public ModelRegression TrainAndFindBestModel(Map<Double,Integer> allContainsFile) {
		
		ModelRegression testModel = CreateModelWithNParam(1);
		
		System.out.println("Start sorter and spliter !");
		Map<String,Map<Double,Integer>> allData = MethodUtils.SortAndSplitDataSet(allContainsFile);
		
		Map<Double,Integer> dataSetTrain = allData.get(Constante.DATASET_LEARN);
		Map<Double,Integer> dataSetAjust = allData.get(Constante.DATASET_AJUST);
		Map<Double,Integer> dataSetTest = allData.get(Constante.DATASET_TEST);
		System.out.println("End sorter and spliter !");
		try {
			System.out.println("Start find sigma !");
			findBestSigma(testModel, dataSetAjust,dataSetTrain);
			System.out.println("End find sigma !");
			
			System.out.println("Start learning");
			//Learn Bitch !
			for(int i = 0; i < Constante.NB_ITERATION_GRADIENT; i++) {
				testModel = doGradientDescent(testModel,dataSetTrain);
			}
			
			System.out.println("End learning");
			
			
			calculScore(testModel, dataSetTest);
		
		}catch(Exception e) {
			
			System.out.println(e.getMessage());
			
		}
		
		// write the value of model in file ?
		
		return testModel;
		
	}
	
	/**
	 * 
	 * @param listModel
	 * @param dataSetTrain
	 */
	private ModelRegression doGradientDescent(ModelRegression model,Map<Double,Integer> dataSetTrain) {
		
		// split data param input and data result output (boolean : 1 : true / 0 : false) 
		List<Double> dataSetInput = new ArrayList<>();
		List<Integer> dataSetOutput = new ArrayList<>();
		for (Map.Entry<Double, Integer> entry : dataSetTrain.entrySet()) {
		
			dataSetInput.add(entry.getKey());
			dataSetOutput.add(entry.getValue());

			
		}
		

		try {
			
			List<Double> valueGradient = MethodUtils.batchGradientLogistiqueOneParam(0.1,dataSetInput,dataSetOutput,model);
			model.updateAllPoid(valueGradient);
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return model;
	}
	
	/**
	 * 
	 * @param listModel
	 * @param dataSetTrain
	 */
	private ModelRegression doGradientDescent(ModelRegression model,List<Double> dataSetInput, List<Integer> dataSetOutput) {
		
		// split data param input and data result output (boolean : 1 : true / 0 : false) 
		
		try {
			
			List<Double> valueGradient = MethodUtils.batchGradientLogistiqueOneParam(0.1,dataSetInput,dataSetOutput,model);
			model.updateAllPoid(valueGradient);
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return model;
	}
	
	
	/**
	 * 
	 * @param model
	 * @param dataSetAjust
	 * @return
	 */
	private void findBestSigma(ModelRegression model, Map<Double,Integer> dataSetAjust, Map<Double,Integer> dataSetTrain) throws Exception{
		
		Double[] listSigmaTest = {0.01 , 0.03 , 0.1 , 0.2 , 0.4 , 0.8 , 0.16 , 0.32 , 0.64 , 1.0 , 1.28 , 2.56 , 5.12 , 10.0 , 20.0 , 30.0};
		Double bestSigma = 1.0;
		Double errorMin = Double.MAX_VALUE;
		
		List<Double> dataSetInputAjust = new ArrayList<>();
		List<Integer> dataSetOutputAjust = new ArrayList<>();
		for (Map.Entry<Double, Integer> entry : dataSetAjust.entrySet()) {
			dataSetInputAjust.add(entry.getKey());
			dataSetOutputAjust.add(entry.getValue());
		}
		
		ModelRegression tempModel = model;
		Integer nbIteration = (Constante.NB_ITERATION_GRADIENT) / 10;
		
		for(Double sigma : listSigmaTest) {
			// do temp model to try the best ajust with predict
			tempModel.setSigma(sigma);
			for(int i = 0; i < nbIteration ; i++) {
//				tempModel = doGradientDescent(model,dataSetTrain);
				tempModel = doGradientDescent(tempModel,dataSetInputAjust,dataSetOutputAjust);
			}
			
			Double error = getErrorPrediction(tempModel,dataSetInputAjust,dataSetOutputAjust);
			if(error <= errorMin) {
				
				errorMin = error;
				bestSigma = sigma;
				
			}
			
		}
		
		model.setSigma(bestSigma);
		
	}
	
	/**
	 * This method compute F1Score and the Recall and Precision of model train and ajust
	 * 
	 * @param model
	 * @param dataSetTest
	 */
	private void calculScore(ModelRegression model, Map<Double,Integer> dataSetTest) throws Exception {
		
		Double F1score = 0.0;
		Double Recall = 0.0;
		Double Precision = 0.0;
		
		// si prediction = true mais que dataSetTest = false  => false positif
		Double FalsePositif = 0.0;
		// si prediction = false mais que dataSetTest = true  => false negatif
		Double FalseNegatif = 0.0;
		// si prediction = true est que dataSetTest = true    => true positif
		Double TruePositif = 0.0;
		// si prediction = false est que dataSetTest = false  => true negatif
		Double TrueNegatif = 0.0;
		
		try {
			for (Map.Entry<Double, Integer> entry : dataSetTest.entrySet()) {
				Boolean realValue = (entry.getValue() == 1) ? true : false;
				Boolean prediction = GetPrediction(entry.getKey(),model);
				if(realValue) {
					if(prediction) {
						TruePositif++;
					}else{
						FalseNegatif++;
					}
				}else {
					if(!prediction) {
						TrueNegatif++;
					}else{
						FalsePositif++;
					}
				}
			}
		}catch(Exception e) {
			
			throw new Exception(e.getMessage());
			
		}
		
		
		
		if(TruePositif != 0) {
			Precision = TruePositif/(TruePositif + FalsePositif);
			Recall = TruePositif/(TruePositif + FalseNegatif);
		}else {
			if(FalsePositif != 0) {
				Precision = TruePositif/(TruePositif + FalsePositif);
			}
			if(FalseNegatif != 0) {
				Recall = TruePositif/(TruePositif + FalseNegatif);
			}
		}
		
		if(Recall != 0 || Precision != 0) {
			F1score = 2*((Precision*Recall)/(Precision+Recall));
		}
		
		
		model.setRecall(Recall);
		model.setPrecision(Precision);
		model.setF1Score(F1score);
		
	}
	
	private Double getErrorPrediction(ModelRegression model, List<Double> Xval, List<Integer> Yval) throws Exception {
		
		Double Error = 0.0;
		try {
			for(int i = 0; i < Xval.size(); i++) {
				
				Double predictionPercentage = MethodUtils.computeHypoteseLogistique(Xval.get(i), model);
				Integer realValue = Yval.get(i);
				Error += predictionPercentage - realValue; 
				
			}
			
			Error = Error / Xval.size();
		}catch(Exception e) {
			
			throw new Exception(e.getMessage());
			
		}
		
		return Error;
		
		
	}
	
	/**
	 * 
	 * @param value
	 * @param bestModel
	 * @return
	 */
	public Boolean GetPrediction(Double value, ModelRegression bestModel) throws Exception {
		
		try {
			
			Double predictionPercentage = MethodUtils.computeHypoteseLogistique(value, bestModel);
		
			if((predictionPercentage * 100) >= Constante.SEUIL_PERCENTAGE_VALUE_VALIDE) {
				return true;
			}
			
			return false;
			
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	
	
	/**
	 * This Method generate list of model regression with different parameter setting
	 * @return
	 */
	private ModelRegression CreateModelWithNParam(Integer nbParam){
		
			ModelRegression modelCreate = new ModelRegression();
			
			for(int i = 0; i < nbParam ; i++) {
				Poid poidGenerate = new Poid();
				List<Integer> exposant = new ArrayList<>();
				exposant.add(1);
				exposant.add(2);
				exposant.add(3);
				exposant.add(4);
				exposant.add(5);
				exposant.add(6);
//				exposant.add(7);
//				exposant.add(8);
//				exposant.add(9);
//				exposant.add(10);
//				exposant.add(11);
				poidGenerate.setExposant(exposant);
				poidGenerate.setValue(0.0);
				modelCreate.addPoid(poidGenerate);
			}
			
		return modelCreate;
	}
	
	

}
