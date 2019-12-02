package com.sebt.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import com.sebt.constante.Constante;

public class ReadFileToConvert {
	
	private static Map<Double,Integer> getFileTransform(String PathFile, Double Bias, Integer GoodOrNot) throws Exception{
		
		Map<Double,Integer> containsFile = new HashMap<>();
		File file = new File(PathFile); 
		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(new FileReader(file));
		String st;
		
		while ((st = br.readLine()) != null) {
			double test = (double) st.hashCode() / 100000000;
			containsFile.put(test,GoodOrNot);
		}
		
		return containsFile; 
		
		
	}


	public static Map<Double,Integer> getGoodQuery() throws Exception {

		return getFileTransform(Constante.PATH_FILE + Constante.FILE_GOOD_QUERY,0.0,0);

	}
	
	public static Map<Double,Integer> getBadQuery() throws Exception{
		
		return getFileTransform(Constante.PATH_FILE +Constante.FILE_BAD_QUERY,0.0,1);
		
	}
	
	
	
	
	
	/*************
	 * 	Test
	 ************/
	
	public static Map<String,Integer> getFile(String PathFile,Integer GoodOrNot) throws Exception{
		
		Map<String,Integer> containsFile = new HashMap<>();
		File file = new File(PathFile); 
		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(new FileReader(file));
		String st;
		while ((st = br.readLine()) != null) {
			containsFile.put(st,GoodOrNot);
		}
		
		return containsFile; 
		
		
	}
	
	
	
	
//	public static Map<String,Integer> getUseRegExBadQuery() throws Exception {
//		
//		return countUseRegEx(Constante.PATH_FILE + Constante.FILE_BAD_QUERY);
//		
//	}
//	
//	public static Map<String,Integer> getUseRegExGoodQuery() throws Exception {
//		
//		return countUseRegEx(Constante.PATH_FILE + Constante.FILE_GOOD_QUERY);
//		
//	}
	
	
//	public static Map<String,Integer> countUseRegEx(String PathFile) throws Exception {
//		
////		char[] otherCara = {',','.',':',';','"','\'','\\','/','?','!','%','*','-','+','&','(',')','[',']','{','}','=','@','#','$','_','<','>',' ','|'};
//		char[] otherCara = {',','.',':','\'','\\','/','?','!','%','*','+','&','[',']','{','}','=','@','#','$','_',' ','|'};
//		
//		Map<String,Integer> containsFile = new HashMap<>();
//		File file = new File(PathFile); 
//		BufferedReader br = new BufferedReader(new FileReader(file));
//		String st;
//		
//		while ((st = br.readLine()) != null) {
//			String newWord = "";
//			for(char c : st.toCharArray()) {
//				if(!valueInList(otherCara,c)) {
//					newWord += c;
//				}else {
//					newWord += " ";
//				}
//			}
//			for(String str : newWord.split(" ")) {
//				if(!str.equals("") && !str.equals(" ") ) {
//					
//					if(containsFile.containsKey(str)) {
//						containsFile.replace(str, containsFile.get(str) + 1);
//					}else {
//						containsFile.put(str, 1);
//					}
//				}
//			}
//			
//		}
//		
//		return containsFile; 
//		
//	}

//	private static boolean valueInList(char[] listExp, char value) {
//		
//		for(char i : listExp) {
//			if(value == i) {
//				return true;
//			}
//		}
//		return false;
//		
//	}
	
//	public static Map<String,Integer> countUseRegEx(String PathFile) throws Exception {
//		
//		String[] regExChar = {",","/","\'","\"","_","-","="," "};
//		String[] listExlude = {"0","1","2","3","4","5","6","7","8","9","10","11","12","13","00","01","02","03","04","05","06","07","08","09"
//								,"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"
//								,"+","-",">","<","[","]","#","%","&"
//								,"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z",};
//		
//		Map<String,Integer> containsFile = new HashMap<>();
//		File file = new File(PathFile); 
//		BufferedReader br = new BufferedReader(new FileReader(file));
//		String st;
//		
//		while ((st = br.readLine()) != null) {
//			String[] splitedSt = null;
//			String split = st;
//			for(String c : regExChar) {
//				if(split.contains(c)) {
//					splitedSt = split.split(c);
//					split = "";
//					for(String w : splitedSt) {
//						split+= w + " ";
//					}
//				}
//			}
//			for(String str : split.split(" ")) {
//				if(!str.equals("") && !str.equals(" ") ) {
//					
//					if(containsFile.containsKey(str)) {
//						containsFile.replace(str, containsFile.get(str) + 1);
//					}else {
//						containsFile.put(str, 1);
//					}
//				}
//			}
//		}
//		for(String str : listExlude) {
//			containsFile.remove(str);
//		}
//		
//		return containsFile; 
//		
//	}

}
