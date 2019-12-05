package com.sebt.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Map;

import com.sebt.constante.Constante;
import com.sebt.model.Data;
import com.sebt.model.ModelRegression;
import com.sebt.model.Poid;

public class WriteFile {
	
	public static void saveModelLearn(ModelRegression model) throws Exception {
		BufferedWriter writer = null;
        try {
            //create a temporary file
            File logFile = new File(Constante.PATH_FILE + Constante.FILE_SAVE_MODEL);

            writer = new BufferedWriter(new FileWriter(logFile));
            int i=0;
            
            writer.write("Result for this model train : \n");
            writer.write("Sigma value : "+model.getSigma() + "\n");
            writer.write("--------------------------------------------- \n");
        	writer.write("Poid number " + i++ + "\n");
        	writer.write("Value set for fixe poid of model : " + model.getFixePoid()+ "\n");
        	writer.write("Exposant : \n");
        	writer.write("--------------------------------------------- \n \n");
            
            for(Poid poid : model.getListPoid()) {
            	writer.write("--------------------------------------------- \n");
            	writer.write("Poid number " + i + "\n");
            	writer.write("Value set : " + poid.getValue() + "\n");
            	writer.write("Exposant : \n");
            	for(Integer expo : poid.getExposant()) {
            		writer.write(expo + " | ");
            	}
            	writer.write("\n--------------------------------------------- \n");
            	i++;
            }
            
            writer.write("\n \n--------------------------------------------- \n");
            writer.write("////////// Precision : " + model.getPrecision() + "\n");
            writer.write("////////// Recall    : " + model.getRecall() + "\n");
            writer.write("////////// F1Score   : " + model.getF1Score() + "\n");
            writer.write("--------------------------------------------- \n");
            
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
	
	public static void saveTransformDataValue(Map<Double,Integer> dataset) throws Exception {
		BufferedWriter writer = null;
        try {
            //create a temporary file
            File logFile = new File(Constante.PATH_FILE_WRITE + "DataSetTransform.txt");

            Integer Space = 50;
            writer = new BufferedWriter(new FileWriter(logFile));
            //Write model 
            for(Map.Entry<Double, Integer> entry : dataset.entrySet()) {
            		String spaceWord = getNSpace(Space - entry.getKey().toString().length());
            		writer.write(entry.getKey() + " " + spaceWord + " " + entry.getValue() +"\n" );
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
            		String spaceWord = getNSpace(Space - entry.getKey().length());
            		writer.write(entry.getKey() + " " + spaceWord + " " + entry.getValue() +"\n" );
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
