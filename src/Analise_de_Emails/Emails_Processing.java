package Analise_de_Emails;

import java.util.ArrayList;

import txtreader.*;

public class Emails_Processing {
	private ArrayList<Email>spam;
	private ArrayList<Email>ham;
	private ArrayList<Double>weights;
	
	public Emails_Processing(ArrayList<Email>spam,ArrayList<Email>ham,ArrayList<Double>weights) {
			this.spam=spam;
			this.ham=ham;
			this.weights=weights;
			
	}
	
	public ArrayList<Email> getSpam() {
		return spam;
	}

	public void setSpam(ArrayList<Email> spam) {
		this.spam = spam;
	}

	public ArrayList<Email> getHam() {
		return ham;
	}

	public void setHam(ArrayList<Email> ham) {
		this.ham = ham;
	}

	public ArrayList<Double> getWeights() {
		return weights;
	}

	public void setWeights(ArrayList<Double> weights) {
		this.weights = weights;
	}

	public int calcFN() {
		int FN = 0;
		for(Email e:spam) {
			double weights_sum=0.0;
			for(Rule r:e.getRules()) {
				weights_sum+=weights.get(r.getId());
				
			}
			if(weights_sum<5.0) {
				FN++;
			}
		}
		return FN;
	} 
		 
	public int calcFP() {
		int FP = 0;
		for(Email e:ham) {
			double weights_sum=0.0;
			for(Rule r:e.getRules()) {
				weights_sum+=weights.get(r.getId());
			}
			if(weights_sum>=5.0) {
				FP++;
			}
		}
		return FP;
	}
}
