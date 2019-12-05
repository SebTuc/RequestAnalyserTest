package com.sebt.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sebt.model.Data;
import com.sebt.model.RegExIndex;
import com.sebt.utils.MethodUtils;

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
	
	private static Map<String,Double> dico = new HashMap<>();
	private static Double indexDico = 0.0;
	private static RegExIndex reg = new RegExIndex();
	
	public static Map<String,Double> generateDictionnary() throws Exception{
		
	
		for(RegExIndex reg : reg.listReg) {
			Character car = reg.getCharacter().get(0);
			String word = car.toString();
			dico.put(word, indexDico);
			indexDico++;
			addNewWordInDico(word);	
		}
		
		return dico;
	}
	
	private static void addNewWordInDico(String word) throws Exception{
		
		for(RegExIndex reg : reg.listReg) {
			Character cara = reg.getCharacter().get(0);
			String newWord = word +cara.toString();
			dico.put(newWord, indexDico);
			indexDico++;
			if(newWord.length() < 4) {
				addNewWordInDico(newWord);
			}
		}
	}
	
	public static String[] getAllWordInRequestEscapeSpecialCaraAndNumber(String request){
//		char[] otherCara = {',',':','"','\'','\\','/','?','!','%','*','+','&','(',')','[',']','{','}','=','@','#','$','_',' ','|','`','^','~'};
//		char[] otherCara = {',','.',':',';','"','\'','\\','/','?','!','%','*','-','+','&','(',')','[',']','{','}','=','@','#','$','_','<','>',' ','|','`','^','~','0','1','2','3','4','5','6','7','8','9'};
//		char[] otherCara = {',','.',':',';','"','\'','\\','/','?','!','%','*','-','+','&','(',')','[',']','{','}','=','@','#','$','_','<','>',' ','|','`','^','~'};
//		char[] otherCara = {';','&','-','<','>',' '};
		char[] otherCara = {';','&','-','<','>',' ','/','.','%'};
//		char[] otherCara = {' '};
		String newWord = "";
		for(char c : request.toCharArray()) {
			if(!MethodUtils.valueInList(otherCara,c)) {
				newWord += c;
			}else {
				newWord += " ";
			}
		}
		
		return newWord.split(" ");
		
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
	
	public static Double sommeOfAllMatchingWord(String test, Map<String,Data> dictionnary) {
		if(test.equals("")) {
			return 0.0;
		}
		Double somme = 0.0;
		Double bonus = 1000000.0;
		double index =1.0;
		String[] splitedString = getAllWordInRequestEscapeSpecialCaraAndNumber(test);
		for(String st : splitedString) {
			if(!st.equals("")) {
				if(dictionnary.containsKey(st)) {
	
					somme+= dictionnary.get(st).getIndex() + st.hashCode();
					if(dictionnary.get(st).getOutput() == 1) {
						
//						somme+= ((dictionnary.get(st).getIndex() * st.hashCode())*bonus)*index  ;
						somme+= (dictionnary.get(st).getIndex() * index) + bonus;
						somme += st.hashCode()/(st.length()*100) * index;
//					}
					}else {
//						
						somme+= dictionnary.get(st).getIndex() * index;
						somme += st.hashCode()/(st.length()*100) * index;
//						somme+= ((dictionnary.get(st).getIndex()) * st.hashCode()) *index;
//						
					}
					
				}else {
					// Faire une verification dans le dictionnaire si il y a une forte ressemblance avec le compare leviathan
					somme += st.hashCode()/(st.length()*100) * index;
				}
				index++;
			}
		}
		
		somme += test.hashCode()/(test.length()*100);
		
		return somme/100000000;
	}
	
	public static Map<Double,Integer> transformeDataToValue(Map<String,Data> dictionnary , Map<String,Integer> allValue) {

		Map<Double,Integer> feature = new HashMap<>();
		
		for(Map.Entry<String, Integer> entry : allValue.entrySet()) {
			
//			feature.put(getMaxSimilarityPositifValue(entry.getKey(),dictionnary), entry.getValue());
			feature.put(sommeOfAllMatchingWord(entry.getKey(),dictionnary), entry.getValue());
		}
	
		return feature;
		
	}
	
		
//	public static Map<Double,Integer> transformeDataToValue(Map<String,Integer> dictionnary , Map<String,Integer> allValue) {
//
//		Map<Double,Integer> feature = new HashMap<>();
//		
//		for(Map.Entry<String, Integer> entry : allValue.entrySet()) {
//			
////			feature.put(getMaxSimilarityPositifValue(entry.getKey(),dictionnary), entry.getValue());
//			
//		}
//	
//		return feature;
//		
//	}
	
	
}
