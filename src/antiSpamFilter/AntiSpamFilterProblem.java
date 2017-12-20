package antiSpamFilter;

import java.util.ArrayList;
import java.util.List;

import org.uma.jmetal.problem.impl.AbstractDoubleProblem;
import org.uma.jmetal.solution.DoubleSolution;

import txtreader.Email;
import txtreader.Leitor;
import txtreader.Rule;

public class AntiSpamFilterProblem extends AbstractDoubleProblem {
		private ArrayList<Email>ham;
		private ArrayList<Email>spam;
		private String srcHam="C:\\Users\\Sergio-PC\\Desktop\\Universidade\\Engenharia de Software\\Projecto\\Inputs\\ham.log";
		private String srcSpam="C:\\Users\\Sergio-PC\\Desktop\\Universidade\\Engenharia de Software\\Projecto\\Inputs\\spam.log";
		private String srcRules="C:\\Users\\Sergio-PC\\Desktop\\Universidade\\Engenharia de Software\\Projecto\\Inputs\\rules.cf";

	  
	  public AntiSpamFilterProblem() {
	    // 10 variables (anti-spam filter rules) by default 
	    this(335);
	  }

	  public void setupData() {
		  Leitor tmp=new Leitor();
		  tmp.read_Rules(srcRules);
		  tmp.read_Email(srcHam);
		  tmp.read_Email(srcSpam);
		  ham=tmp.getHam();
		  spam=tmp.getSpam();
		  tmp=null;
	  }
	  
	  public AntiSpamFilterProblem(Integer numberOfVariables) {
		//-----------inicializar variaveis necessarias---------------//
		setupData();
		//---------------------------------------//
	    setNumberOfVariables(numberOfVariables);
	    setNumberOfObjectives(2);
	    setName("AntiSpamFilterProblem");

	    List<Double> lowerLimit = new ArrayList<>(getNumberOfVariables()) ;
	    List<Double> upperLimit = new ArrayList<>(getNumberOfVariables()) ;

	    for (int i = 0; i < getNumberOfVariables(); i++) {
	      lowerLimit.add(-5.0);
	      upperLimit.add(5.0);
	    }

	    setLowerLimit(lowerLimit);
	    setUpperLimit(upperLimit);
	  }

	  public void evaluate(DoubleSolution solution){
	    double[] fx = new double[getNumberOfObjectives()];
	    double[] x = new double[getNumberOfVariables()];
	    for (int i = 0; i < solution.getNumberOfVariables(); i++) {
	    	x[i] = solution.getVariableValue(i) ;
	    }
	    
	    int fn=calcFN(x);
	    int fp=calcFP(x);
	    
	    fx[0]=0.0+fp;
	    fx[1]=0.0+fn;
	    
	    solution.setObjective(0, fx[0]);
	    solution.setObjective(1, fx[1]);
	  }
	  
	  private int calcFN(double[]x) {
			int FN = 0;
			for(Email e:spam) {
				double weights_sum=0.0;
				for(Rule r:e.getRules()) {
					weights_sum+=x[r.getId()];
				}
				if(weights_sum<5.0) {
					FN++;
				}
			}
			return FN;
		} 
		
	  private int calcFP(double[]x) {
			int FP = 0;
			for(Email e:ham) {
				double weights_sum=0.0;
				for(Rule r:e.getRules()) {
					weights_sum+=x[r.getId()];
				}
				if(weights_sum>=5.0) {
					FP++;
				}
			}
			return FP;
		}
	  
	}
