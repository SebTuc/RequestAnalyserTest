package com.sebt.model;

import java.util.ArrayList;
import java.util.List;

public class ModelRegression {
	
	
	private List<Poid> ListPoid;
	
	private Double FixePoid;
	
	private Double F1Score;
	
	private Double Precision;
	
	private Double Recall;
	
	private Double Sigma;


	public ModelRegression() {
		
		this.ListPoid = new ArrayList<>();
		this.Sigma = 1.0;
		this.F1Score = 0.0;
		this.Precision =0.0;
		this.Recall = 0.0;
		// The fixe poid
		this.FixePoid =0.0;
	}
	

	public void updateAllPoid(List<Double> list) throws Exception {
		
		if((this.ListPoid.size() + 1) == list.size()) {
			//Update the first in list is the fixe poid
			this.FixePoid = list.get(0);
			
			//Update other in list
			for(int i = 1; i < list.size(); i++) {

				this.ListPoid.get(i - 1).setValue(list.get(i));
				
			}
		}else {
			
			throw new Exception("size of list you want update it's not equal of list in this model");
		}
		
	}
	
	

	public Double getFixePoid() {
		return FixePoid;
	}
	
	public void addPoid(Poid poid) {
		this.ListPoid.add(poid);
	}
	
	public List<Poid> getListPoid() {
		return ListPoid;
	}

	public Double getSigma() {
		return Sigma;
	}


	public void setSigma(Double sigma) {
		Sigma = sigma;
	}

	
	public void setListPoid(List<Poid> listPoid) {
		ListPoid = listPoid;
	}


	public Double getF1Score() {
		return F1Score;
	}

	public void setF1Score(Double f1Score) {
		F1Score = f1Score;
	}

	public Double getPrecision() {
		return Precision;
	}

	public void setPrecision(Double precision) {
		Precision = precision;
	}

	public Double getRecall() {
		return Recall;
	}

	public void setRecall(Double recall) {
		Recall = recall;
	}
	

}
