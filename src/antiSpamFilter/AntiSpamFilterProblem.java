package antiSpamFilter;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.uma.jmetal.problem.impl.AbstractDoubleProblem;
import org.uma.jmetal.solution.DoubleSolution;

import Analise_de_Emails.Emails_Processing;
import txtreader.Email;
import txtreader.Leitor;
import txtreader.Rule;
import txtreader.Type;

public class AntiSpamFilterProblem extends AbstractDoubleProblem {
	private Leitor tmp;
	  
	  public AntiSpamFilterProblem() {
	    // 10 variables (anti-spam filter rules) by default 
	    this(335);
	    tmp = new Leitor();
	    tmp.ler_emails("C:\\Users\\Sergio-PC\\Desktop\\Universidade\\Engenharia de Software\\Projecto\\Inputs\\ham.log");
	    tmp.ler_emails("C:\\Users\\Sergio-PC\\Desktop\\Universidade\\Engenharia de Software\\Projecto\\Inputs\\spam.log");
	    tmp.ler_Regras("C:\\Users\\Sergio-PC\\Desktop\\Universidade\\Engenharia de Software\\Projecto\\Inputs\\rules.cf");
	    
	  }

	  public AntiSpamFilterProblem(Integer numberOfVariables) {
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
	    
	    /*Implementação do falso positivos e falsos negativos*/
	    int falsos_negativos=0;
	    int falsos_positivos=0;
	    ArrayList<String> tmp_para_leitura = tmp.get_RulesNames();
	    //corre a lista de emails (email a email)
	    for(Email j:tmp.get_Emails()) {
	    	//corre a lista de nomes de regras (nome a nome)
	    	double somatorio_de_regras = 0.0;
	    	int size = j.getRegras().size();
	    	int contadorSize=0;
	    	for(int k=0;k<tmp_para_leitura.size();k++){
	    		//vai ver se o nome da regra esta dentro da lista de regras do email
	    		if(j.getRegras().contains(tmp_para_leitura.get(k))) {
	    			somatorio_de_regras+=x[k];
	    			contadorSize++;
	    		}
	    		if(contadorSize==size)
	    			break;
	    	}
	    	if(somatorio_de_regras>5.0 && j.getTipo().equals(Type.HAM)) {
	    		falsos_positivos++;
	    	}
	    	if(somatorio_de_regras<5.0 && j.getTipo().equals(Type.SPAM)) {
	    		falsos_negativos++;
	    	}
	    }
	    
	    
	    fx[0]=0.0+falsos_positivos;
	    fx[1]=0.0+falsos_negativos;
	    
	    
	    
	    
	    /*----------------------------------------------*/
	    
//	    fx[0]=0.0;
//	    fx[1]=0.0; 
	    
//	    System.out.println("FP"+fx[0] + "  FN"+fx[1]);
	    
	    solution.setObjective(0, fx[0]);
	    solution.setObjective(1, fx[1]);
		  
	  }
	}
