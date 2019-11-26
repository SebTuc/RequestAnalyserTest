package com.sebt.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import com.sebt.constante.Constante;

public class ReadFileToConvert {
	
	private static Map<Double,Integer> getFile(String PathFile, Double Bias, Integer GoodOrNot) throws Exception{
		
		Map<Double,Integer> containsFile = new HashMap<>();
		File file = new File(PathFile); 
		BufferedReader br = new BufferedReader(new FileReader(file));
		String st;
		
		while ((st = br.readLine()) != null) {
			Double somme = Bias;
			Double index = 1.0;
			for(char c : st.toCharArray()) {
				
				Double value = (((double)c) * index) ;
				somme+= value ;
				index*=10;
				
			}
			containsFile.put(somme/(index*10),GoodOrNot);
//		    System.out.println(st); 
		}
		
		return containsFile; 
		
		
	}


	public static Map<Double,Integer> getGoodQuery() throws Exception {

		return getFile(Constante.PATH_FILE + Constante.FILE_GOOD_QUERY,0.0,0);

	}
	
	public static Map<Double,Integer> getBadQuery() throws Exception{
		
		return getFile(Constante.PATH_FILE +Constante.FILE_BAD_QUERY,0.0,1);
		
	}

}
