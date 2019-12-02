package com.sebt.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sebt.constante.Constante;

public class Dataset {
	
	
	private List<DataFile> dataset = new ArrayList<>();
	
	public Dataset(Map<String,Integer> containsFile) {
		
		for(Map.Entry<String, Integer> entry : containsFile.entrySet()) {
			Integer output = entry.getValue();
			String input = entry.getKey();
			if(!input.equals("")) {
				this.dataset.add(new DataFile(input,output));
			}
		}
	}
	
	private Dataset() {
		
	}
	
	public Map<String, Dataset> sortAndSplitDataSet(){
		Map<String, Dataset> splited = new HashMap<>();

		splited.put(Constante.DATASET_LEARN, new Dataset());
		splited.put(Constante.DATASET_AJUST, new Dataset());
		splited.put(Constante.DATASET_TEST, new Dataset());

		for (DataFile dataFile : this.dataset) {
			Integer random = (int)Math.ceil(Math.random() * 100);
			if(random < Constante.PERCENTAGE_FOR_DATASET_LEARN) {
				splited.get(Constante.DATASET_LEARN).addDataFile(dataFile);
			}else if(random < (Constante.PERCENTAGE_FOR_DATASET_LEARN + Constante.PERCENTAGE_FOR_DATASET_AJUST)) {
				splited.get(Constante.DATASET_AJUST).addDataFile(dataFile);
			}else {
				splited.get(Constante.DATASET_TEST).addDataFile(dataFile);
			}
			

		}
		
		return splited;
		
	}
	
	private void addDataFile(DataFile dataFile) {
		this.dataset.add(dataFile);
	}
	
	public List<DataFile> mergeOtherDataset(Dataset mergeDataSet){
		this.dataset.addAll(mergeDataSet.dataset);
		return dataset;
		
	}
	
	public List<DataFile> getDataset() {
		return dataset;
	}

}
