package txtreader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import txtreader.rule;

public class leitor {
	
	private ArrayList<rule>regras;
	private ArrayList<email>emails;
	
	public leitor() {
		regras=new ArrayList<rule>();
		emails=new ArrayList<email>();
	}
	
	public void ler_Regras() {
		try {
			Scanner sc = new Scanner(new File("rules.cf"));
			int contador=0;
			String linha="";
			while(sc.hasNextLine()) {
				linha=sc.nextLine();
				regras.add(new rule(linha, 0.0));
			}
			System.out.println(contador);
			sc.close();
		} catch (FileNotFoundException e) {
			System.out.println("Ficheiro não foi encontrado");
		}
	}
	
	public void ler_emails(String fonte) {
		try {
			String source=fonte;
			type tipo = type_definition(source);
			String linha="";
			Scanner sc = new Scanner(new File(source));
			while(sc.hasNextLine()) {
				linha=sc.nextLine();
				tratar_email(linha, tipo);
			}
			sc.close();
		} catch (FileNotFoundException e) {
			System.out.println("Ficheiro não foi encontrado");
		} catch (IllegalStateException e) {
			System.out.println("Ficheiro que que selecionou nao estou correcto");
			e.getStackTrace();
		}
		
	}
	
	public void imprimir_resultados(){
		for(int i=0;i<regras.size();i++) {
			System.out.println(regras.get(i));
		}
		for(int j=0;j<emails.size();j++) {
			System.out.println(emails.get(j));
		}
	}
	
	private type type_definition(String source) {
		type tipo = null;
		if(source.contains("ham"))
			tipo=type.HAM;
		else if(source.contains("spam"))
			tipo=type.SPAM;
		else
			tipo=null;
		return tipo;
	}
	
	private void tratar_email(String linha, type tipo) {
		String []tmpVector = linha.split("	");
		email tmpEmail = new email(tmpVector[0], tipo);
		for(int i=1;i<tmpVector.length;i++) {
			tmpEmail.adicionar_Regras(tmpVector[i]);
		}
		emails.add(tmpEmail);
	}

	public ArrayList<email> get_Emails() {
		return emails;
	}
	
	public ArrayList<rule> get_Regras(){
		return regras;
	}
}