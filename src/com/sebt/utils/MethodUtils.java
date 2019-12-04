package com.sebt.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sebt.constante.Constante;
import com.sebt.model.ModelRegression;
import com.sebt.model.Poid;

public class MethodUtils {
	
	
	private static final String ERROR_SIZE_PARAM_TRAINING_NOT_SAME_SIZE_POID_MODEL = "This model not contains lot of parameter";
	private static final String ERROR_LEARNING_RATE_TO_SLOW = "Learning rate not can behind 0 or equal 0";
	
	/**
	 * method return the sum of list Double
	 * @param list
	 * @return
	 */
	public static Double sommeListDouble(List<Double> list) {
		Double somme=0.0;
		
		for(Double value : list) {
			
			somme+=value;
		}
		
		return somme;
		
	}
	
	/**
	 * SplitData with parameter in constante
	 * @param allData
	 * @return
	 */
	public static Map<String, Map<Double,Integer>> SortAndSplitDataSet(Map<Double,Integer> allData){
		Map<String, Map<Double,Integer>> splited = new HashMap<>();

		splited.put(Constante.DATASET_LEARN, new HashMap<>());
		splited.put(Constante.DATASET_AJUST, new HashMap<>());
		splited.put(Constante.DATASET_TEST, new HashMap<>());

		for (Map.Entry<Double, Integer> entry : allData.entrySet()) {
			Integer random = (int)Math.ceil(Math.random() * 100);
			if(entry != null) {
				if(random < Constante.PERCENTAGE_FOR_DATASET_LEARN) {
					splited.get(Constante.DATASET_LEARN).put(entry.getKey(),entry.getValue());
				}else if(random < (Constante.PERCENTAGE_FOR_DATASET_LEARN + Constante.PERCENTAGE_FOR_DATASET_AJUST)) {
					splited.get(Constante.DATASET_AJUST).put(entry.getKey(),entry.getValue());
				}else {
					splited.get(Constante.DATASET_TEST).put(entry.getKey(),entry.getValue());
				}
			}

		}
		
		return splited;
	}
	
	/**
	 * Get min and max and mean value in one time to minimize calcul
	 * @param list
	 * @return
	 */
	public static String getMinAndMaxAndMeanValueInString(List<Double> list) {
		
		
		Double minValue = Double.MAX_VALUE;
		Double maxValue = Double.MIN_VALUE;
		Double somme = 0.0;
		for(Double value : list) {
			if(value < minValue) {
				minValue = value;
			}
			if(value > maxValue) {
				maxValue = value;
			}
			somme+=value;
		}
		
		String value = minValue.toString() + " " + maxValue.toString() + " " + (somme/list.size());
		
		return value;
	}
	
	/**
	 * 
	 * @param map
	 * @return
	 */
	public static String getMinAndMaxAndMeanValueInStringForMap(Map<Double,Integer> map) {
		
		
		Double minValue = Double.MAX_VALUE;
		Double maxValue = Double.MIN_VALUE;
		Double somme = 0.0;
		for(Map.Entry<Double, Integer> entry : map.entrySet()) {
			Double value = entry.getKey();
			if(value < minValue) {
				minValue = value;
			}
			if(value > maxValue) {
				maxValue = value;
			}
			somme+=value;
		}
		
		String value = minValue.toString() + " " + maxValue.toString() + " " + (somme/map.size());
		
		return value;
	}
	
	/**
	 * Get min and max and mean value in one time to minimize calcul
	 * @param list
	 * @return
	 */
	public static String getMinAndMaxAndMeanValueInString(Map<Double,Boolean> list) {
		
		
		Double minValue = Double.MAX_VALUE;
		Double maxValue = Double.MIN_VALUE;
		Double somme = 0.0;
		for(Double value : list.keySet()) {
			if(value < minValue) {
				minValue = value;
			}
			if(value > maxValue) {
				maxValue = value;
			}
			somme+=value;
		}
		
		String value = minValue.toString() + " " + maxValue.toString() + " " + (somme/list.size());
		
		return value;
	}
	
	
	/**
	 * method to normalize value 
	 * @param value
	 * @param maxValue
	 * @param minValue
	 * @param meanValue
	 * @return
	 */
	public static Double normalizeValue(Double value, Double minValue, Double maxValue,Double meanValue) {
		
		return (value - meanValue)/(maxValue-minValue);
		
	}
	
	public static Map<Double,Integer> normalizeMap(Map<Double,Integer> mapValue,Double minValue, Double maxValue, Double meanValue){
		Map<Double,Integer> newMap = new HashMap<>();
		for(Map.Entry<Double, Integer> entry : mapValue.entrySet()) {
			
			newMap.put(normalizeValue(entry.getKey(),minValue,maxValue,meanValue), entry.getValue());
			
		}
		
		return newMap;
	}
	
	/**
	 * method to comput the sigmoid of value input
	 * @param z
	 * @return
	 */
	public static Double functionSigmoide(Double z) {
		
		return 1.0/(1+Math.exp(z));
		
	}
	
	/**
	 * this method compute the hypotese of value 
	 * @param x
	 * @param poid
	 * @throws Exception
	 * @return
	 */
	public static Double computeHypoteseLogistique(List<Double> x, ModelRegression model) throws Exception{
		Double result = 0.0;
		//Compute with many parameter in model, first find if the model contains the same size of poid and the size of x
		if(model.getListPoid().size() != x.size()) {
			throw new Exception(ERROR_SIZE_PARAM_TRAINING_NOT_SAME_SIZE_POID_MODEL);
		}
		// it's ok here
		for(int i = 0; i < model.getListPoid().size(); i++) {
			
			Poid poid = model.getListPoid().get(i);
			Double valuePoid = poid.getValue();
			Double valueX = x.get(i);
			
			if(valuePoid != 0.0) {
				for(Integer expo : poid.getExposant()) {
					
					Double resultTemp = 1.0;
					for(int j = 1 ; j < expo; j++) {
						resultTemp *= valueX;
					}
					result += resultTemp * valuePoid;
					
				}
			}
		}
		
		result += model.getFixePoid();
		
		return functionSigmoide(result);
	}
	
	/**
	 * this method compute the hypotese of value 
	 * @param x
	 * @param poid
	 * @throws Exception
	 * @return
	 */
	public static Double computeHypoteseLogistique(Double x, ModelRegression model) throws Exception{
		Double result = 0.0;
		
		//Compute with many parameter in model, first find if the model contains the same size of poid and the size of x
		if(model.getListPoid().size() != 1) {
			throw new Exception(ERROR_SIZE_PARAM_TRAINING_NOT_SAME_SIZE_POID_MODEL);
		}
		// it's ok here

			
		Poid poid = model.getListPoid().get(0);
		Double valuePoid = poid.getValue();
		Double valueX = x;
			
		if(valuePoid != 0.0) {
			for(Integer expo : poid.getExposant()) {
					
				Double resultTemp = 1.0;
				for(int j = 1 ; j < expo; j++) {
					resultTemp *= valueX;
				}
				result += resultTemp * valuePoid;
					
			}
		}
		
		// Fixe Poid
		result += model.getFixePoid();
		
		return functionSigmoide(result);
	}
	
	/**
	 * method for regression logistic to compute the cost function of unique value x
	 * @param x
	 * @param y
	 * @param poid
	 * @param sigma
	 * @throws Exception
	 * @return
	 */
	public static Double costFunctionLogistique(Double x, Integer y, ModelRegression model) throws Exception {
		
//		Double result = 0.0;
		
//		return result;
		
		throw new Exception("This method is not implement");

	}
	

	/**
	 * method for regression logistic to compute the cost function
	 * @param x
	 * @param y
	 * @param poid
	 * @param sigma
	 * @throws Exception
	 * @return
	 */
	public static Double costFunctionLogistique(List<Double> x, List<Integer> y, ModelRegression model) throws Exception {
		
		//Compute hypothese minus the result y dans sum all value dividide by nbParameter
//		Double result = 0.0;
		
//		return result;
		
		throw new Exception("This method is not implement");
		
	}
	
	
	/**
	 * This method compute batchGradient descent and return the list of temps value of decrease current poid (the first is the fixe poid)
	 * 
	 * @param x
	 * @param y
	 * @param poids
	 * @throws Exception
	 * @return
	 */
	public static List<Double> batchGradientLogistique(Double learningRate, List<List<Double>> dataSetInput, List<Integer> dataSetOutput, ModelRegression model) throws Exception {
		
		if(dataSetInput.get(0).size() != model.getListPoid().size()) {
			
			throw new Exception(ERROR_SIZE_PARAM_TRAINING_NOT_SAME_SIZE_POID_MODEL);
			
		}
		
		if(learningRate <= 0 ) {
			
			throw new Exception(ERROR_LEARNING_RATE_TO_SLOW);
			
		}
		
		List<Double> result = new ArrayList<>();
		
		// Fixe poid
		Double sum = 0.0;
		for(int j = 0; j<dataSetInput.size(); j++) {
			
			sum += batchGradientLogistiqueForFixePoid(dataSetInput.get(j),dataSetOutput.get(j),model);
		}
		Double valueGradientTempFixe = learningRate * (sum / dataSetInput.size());
		result.add(valueGradientTempFixe);
		
		
		// List poid
		for(int i = 0; i < model.getListPoid().size(); i++) {
			Double valueGradientTemp;
			sum = 0.0;
			for(int j = 0; j<dataSetInput.size(); j++) {
				
				sum += batchGradientLogistiqueOneTraining(dataSetInput.get(j),dataSetInput.get(j).get(i),dataSetOutput.get(j),model);
			}
			
			
			Double sumPoid = model.getListPoid().get(i).getValue() * model.getSigma();
			valueGradientTemp =  learningRate * ((sum / dataSetInput.size()) + (sumPoid / dataSetInput.size()));
			valueGradientTemp = learningRate * (sum / dataSetInput.size());
			
			result.add(valueGradientTemp);
		}
		
		return result;
		
	}
	
	
	/**
	 * This method compute batchGradient descent and return the list of temps value of decrease current poid (the first is the fixe poid)
	 * 
	 * @param x
	 * @param y
	 * @param poids
	 * @throws Exception
	 * @return
	 */
	public static List<Double> batchGradientLogistiqueOneParam(Double learningRate, List<Double> dataSetInput, List<Integer> dataSetOutput, ModelRegression model) throws Exception {
		
		List<Double> result = new ArrayList<>();
		
		if(model.getListPoid().size() != 1) {
			
			throw new Exception(ERROR_SIZE_PARAM_TRAINING_NOT_SAME_SIZE_POID_MODEL);
			
		}
		
//		if(dataSetOutput.size() != model.getListPoid().size() && dataSetInput.size() != model.getListPoid().size()) {
//			
//			throw new Exception(ERROR_SIZE_PARAM_TRAINING_NOT_SAME_SIZE_POID_MODEL);
//			
//		}
		
		if(learningRate <= 0 ) {
			
			throw new Exception(ERROR_LEARNING_RATE_TO_SLOW);
			
		}
		
		// Fixe poid
		Double sum = 0.0;
		for(int j = 0; j<dataSetInput.size(); j++) {
			
			sum += batchGradientLogistiqueForFixePoid(dataSetInput.get(j),dataSetOutput.get(j),model);
		}
		Double valueGradientTempFixe = learningRate * (sum / dataSetInput.size());
		result.add(valueGradientTempFixe);
		
		
		// List poid

		Double valueGradientTemp;
		sum = 0.0;
		for(int j = 0; j<dataSetInput.size(); j++) {
				
			sum += batchGradientLogistiqueOneTraining(dataSetInput.get(j),dataSetInput.get(j),dataSetOutput.get(j),model);
			
		}
		
		Double sumPoid = model.getListPoid().get(0).getValue() * model.getSigma();
		valueGradientTemp =  learningRate * ((sum / dataSetInput.size()) + (sumPoid / dataSetInput.size()));
		
		result.add(valueGradientTemp);
		
		
		return result;
		
	}
	
	/**
	 * This method compute just one iteration of gradientDescent, 
	 * this method work only if model.listPoid.size = 1 and the dataset contains just one parameter
	 * 
	 * @param x
	 * @param y
	 * @param poids
	 * @throws Exception
	 * @return
	 */
	public static Double batchGradientLogistiqueOneTraining(List<Double> listParam, Double x, Integer y, ModelRegression model) throws Exception {
		
		return ((computeHypoteseLogistique(listParam,model) - y) * x) ;
		
	}
	
	public static Double batchGradientLogistiqueOneTraining(Double oneParam, Double x, Integer y, ModelRegression model) throws Exception {
		
		return ((computeHypoteseLogistique(oneParam,model) - y) * x) ;
		
	}
	
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @param model
	 * @throws Exception
	 * @return
	 */
	public static Double batchGradientLogistiqueForFixePoid(Double x, Integer y, ModelRegression model) throws Exception {
		
		return ((computeHypoteseLogistique(x,model) - y));
		
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public static Double batchGradientLogistiqueForFixePoid(List<Double> x, Integer y, ModelRegression model) throws Exception {
		
		return ((computeHypoteseLogistique(x,model) - y));
		
	}
	
	public static boolean valueInList(char[] listExp, char value) {
		
		for(char i : listExp) {
			if(value == i) {
				return true;
			}
		}
		return false;
		
	}
	
}
