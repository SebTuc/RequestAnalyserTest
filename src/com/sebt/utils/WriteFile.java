package com.sebt.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Map;

import com.sebt.constante.Constante;
import com.sebt.model.Data;
import com.sebt.model.ModelRegression;

public class WriteFile {
	
	public static void saveModelLearn(ModelRegression model) throws Exception {
		BufferedWriter writer = null;
        try {
            //create a temporary file
            File logFile = new File(Constante.PATH_FILE + Constante.FILE_SAVE_MODEL);

            writer = new BufferedWriter(new FileWriter(logFile));
            //Write model 
            writer.write("Model here");
            
            
        }finally {
           
        	writer.close();
        	
        }
	}
	
	public static void saveDataTraining(Map<String,Data> listData,String nameFile) throws Exception{
		
		BufferedWriter writer = null;
        try {
            //create a temporary file
            File logFile = new File(Constante.PATH_FILE_WRITE + nameFile);
            writer = new BufferedWriter(new FileWriter(logFile));
            //Write model 
            Integer Space = 50;
            for(Map.Entry<String, Data> entry : listData.entrySet()) {
            	
            	String spaceWord = getNSpace(Space - entry.getKey().length());
            	writer.write(entry.getKey() + spaceWord + entry.getValue().getOutput()+" "+ entry.getValue().getIndex() + "\n" );
            	
            }
            
        }finally {
           
        	writer.close();
        	
        }
		
	}
	
	public static void saveUseRegInTrainingExemple(Map<String,Integer> useReg, String nameFile) throws Exception {
		Integer maxIterationToSave = 30;
		BufferedWriter writer = null;
        try {
            //create a temporary file
            File logFile = new File(Constante.PATH_FILE_WRITE + nameFile);

            Integer Space = 50;
            writer = new BufferedWriter(new FileWriter(logFile));
            //Write model 
            for(Map.Entry<String, Integer> entry : useReg.entrySet()) {
            	if(entry.getValue() > maxIterationToSave) {
            		String spaceWord = getNSpace(Space - entry.getKey().length());
            		writer.write(entry.getKey() + " " + spaceWord + " " + entry.getValue() +"\n" );
            	}
            }
            
        }finally {
           
        	writer.close();
        	
        }
	}
	
	private static String getNSpace(Integer nSpace) {
		String space = "-";
		for(int i=0; i<nSpace - 1;i++) {
			space+="-";
		}
		return space;
	}

}
