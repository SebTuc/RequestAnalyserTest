package com.sebt.constante;

import com.sebt.model.ModelRegression;

public class Constante {

		
//	public static final String FILE_GOOD_QUERY = "goodqueries_temp_small.txt";
//	public static final String FILE_GOOD_QUERY = "goodqueries_temp.txt";
	public static final String FILE_GOOD_QUERY = "goodqueries.txt";
//	public static final String FILE_BAD_QUERY = "badqueries_temp_small.txt";
//	public static final String FILE_BAD_QUERY = "badqueries_temp.txt";
	public static final String FILE_BAD_QUERY = "badqueries.txt";
	
	public static final String PATH_FILE = "C:\\Dev\\Temp\\";
	public static final String PATH_FILE_WRITE = "C:\\Dev\\Temp\\write\\";
	
	public static final String FILE_SAVE_MODEL = "savedModel.txt";
	
	public static final String FILE_SAVE_USE_BAD_QUERY = "saveBadQueryUse.txt";
	public static final String FILE_SAVE_USE_GOOD_QUERY = "saveGoodQueryUse.txt";
	
	public static final String DATASET_LEARN = "learningData";
	public static final String DATASET_AJUST = "ajustData";
	public static final String DATASET_TEST = "testData";
	
	public static final Integer PERCENTAGE_FOR_DATASET_LEARN = 80;
	public static final Integer PERCENTAGE_FOR_DATASET_AJUST = 00;
	public static final Integer PERCENTAGE_FOR_DATASET_TEST = 20;
	
	public static final Integer NB_ITERATION_GRADIENT = 100;
	
	public static final Integer SEUIL_PERCENTAGE_VALUE_VALIDE =95;
	
	public static ModelRegression BEST_MODEL;
	
}
