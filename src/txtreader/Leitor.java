package txtreader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Leitor {
	
	private ArrayList<String> rules;
	private ArrayList<Email> spam;
	private ArrayList<Email> ham;
	private ArrayList<Double> weights;
	private int nRegras;
	private int contadorEmails=0;
	
	public Leitor() {
		rules = new ArrayList<String>();
		spam = new ArrayList<Email>();
		ham = new ArrayList<Email>();
		weights= new ArrayList<Double>();
	}
	
	public void read_Rules(String source) {
		int contador=0;
		try {
			Scanner sc = new Scanner(new File(source));
			String [] ruleContent=null;
			while(sc.hasNextLine()) {
				ruleContent=sc.nextLine().split("	");
				if(ruleContent.length<2) {
					rules.add(ruleContent[0]);
					weights.add(0.0);
				}else {
					rules.add(ruleContent[0]);
					weights.add(Double.parseDouble(ruleContent[1]));
				}
				contador++;
			}
			sc.close();
			ruleContent=null;
			nRegras=contador;
		} catch (FileNotFoundException e) {
			System.out.println("Ficheiro não foi encontrado");
		}
		
	}
	
	public void write_Rules(String source) {
		PrintWriter pw;
		try {
			pw = new PrintWriter(new FileOutputStream(new File(source)));
			pw.flush();
			for(int i=0;i<rules.size();i++) {
				pw.println(rules.get(i)+"	"+weights.get(i));
			}
			pw.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void read_Email(String source){
		try {
			Scanner sc = new Scanner(new File(source));
			while(sc.hasNextLine()) {
				tratarLine(sc.nextLine(),source);
				contadorEmails++;
			}
			sc.close();
			System.out.println(contadorEmails);
		} catch (FileNotFoundException e) {
			System.out.println("Ficheiro não foi encontrado");
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
				//System.out.println("Regras:"+ruleName+" não está contida na lista de regras");
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
