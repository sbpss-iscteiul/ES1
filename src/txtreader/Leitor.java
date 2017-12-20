package txtreader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Leitor {
	
	private ArrayList<String> rules;
	private ArrayList<String> frules;
	public void setRules(ArrayList<String> rules) {
		this.rules = rules;
	}

	public void setWeights(ArrayList<Double> weights) {
		this.weights = weights;
	}

	private ArrayList<Email> spam;
	private ArrayList<Email> ham;
	private ArrayList<Double> weights;
	private ArrayList<Double> fweights;
	private int nRegras;
	private int contadorEmails=0;
	boolean firstTime;
	
	public Leitor() {
		rules = new ArrayList<String>();
		spam = new ArrayList<Email>();
		ham = new ArrayList<Email>();
		weights= new ArrayList<Double>();
		fweights= new ArrayList<Double>();
		frules = new ArrayList<String>();
		firstTime=true;
	}
	
	public void read_Rules(String source) {
		int contador=0;
		rules.clear();
		try {
			Scanner sc = new Scanner(new File(source));
			String [] ruleContent=null;
			while(sc.hasNextLine()) {
				ruleContent=sc.nextLine().split(" ");
				if(ruleContent.length<2) {
					rules.add(ruleContent[0]);
					if(firstTime) { fweights.add(0.0);}
					weights.add(0.0);
 				}else {
					rules.add(ruleContent[0]);
					weights.add(Double.parseDouble(ruleContent[1]));
					if(firstTime) {fweights.add(Double.parseDouble(ruleContent[1]));}
				}
				if(firstTime) {frules.add(ruleContent[0]);}
				contador++;
			}
			sc.close();
			firstTime=false;
			ruleContent=null;
			nRegras=contador;
		} catch (FileNotFoundException e) {
			System.out.println("Ficheiro n�o foi encontrado");
		}
		
	}
	
	private void write_Rules(String source, ArrayList<String> rules, ArrayList<Double> weights) {
		PrintWriter pw;
		try {
			pw = new PrintWriter(new FileOutputStream(new File(source)));
			pw.flush();
			for(int i=0;i<rules.size();i++) {
				pw.println(rules.get(i)+" "+weights.get(i));
			}
			pw.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	public void write_Rules(String source) {
		write_Rules(source,rules,weights);
	}
	public void reset_Rules(String source) {
		write_Rules(source,frules,fweights);
	}
	
	public void read_Email(String source){

		try {
			Scanner sc = new Scanner(new File(source));
			while(sc.hasNextLine()) {
				tratarLine(sc.nextLine(),source);
				contadorEmails++;
			}
			sc.close();
//			System.out.println(contadorEmails);
		} catch (FileNotFoundException e) {
			System.out.println("Ficheiro n�o foi encontrado");
		}
	}
	
	private void tratarLine(String nextLine, String source) {
		String [] line_content=nextLine.split("	");
		Email tmp = new Email(line_content[0]);
		for(int i=1;i<line_content.length;i++) {
			String ruleName=line_content[i];
			try {
				int id = listPosition(ruleName);
				if(id==-1)
					throw new IllegalArgumentException();
				tmp.add_Rules(new Rule(ruleName, id));
			} catch (IllegalArgumentException e) {
				//System.out.println("Regras:"+ruleName+" n�o est� contida na lista de regras");
			}
		}
		if(source.contains("spam.log")) {
			spam.add(tmp);
		}else if(source.contains("ham.log")) {
			ham.add(tmp);
		}
	}
	
	private int listPosition(String ruleName) {
		for(int i=0;i<rules.size();i++) {
			if(rules.get(i).equals(ruleName)) {
				return i;
			}
		}
		return -1;
	}

	public ArrayList<String> getRules() {
		return rules;
	}

	public ArrayList<Email> getSpam() {
		return spam;
	}

	public ArrayList<Email> getHam() {
		return ham;
	}

	public ArrayList<Double> getWeights() {
		return weights;
	}
	
	public void setWeights(int pos,double peso) {
		weights.set(pos, peso);
	}

	public int getnRegras() {
		return nRegras;
	}
		
	
	
	
	
	
	
	
}
