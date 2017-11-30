package txtreader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import txtreader.Rule;

public class Leitor {
	
	private ArrayList<Rule>regras;
	private ArrayList<Email>emails;
	
	public Leitor() {
		regras=new ArrayList<Rule>();
		emails=new ArrayList<Email>();
	}
	

	public void ler_Regras(String source) {
		try {
			Scanner sc = new Scanner(new File(source));
			int contador=0;
			String linha="";
			while(sc.hasNextLine()) {
				linha=sc.nextLine();
				regras.add(new Rule(linha, 0.0));
			}
			sc.close();
		} catch (FileNotFoundException e) {
			System.out.println("Ficheiro não foi encontrado");
		}
	}
	
	public void ler_emails(String fonte) {
		try {
			String source=fonte;
			Type tipo = type_definition(source);
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
	
	private Type type_definition(String source) {
		Type tipo = null;
		if(source.contains("ham"))
			tipo=Type.HAM;
		else if(source.contains("spam"))
			tipo=Type.SPAM;
		else
			tipo=null;
		return tipo;
	}
	
	private void tratar_email(String linha, Type tipo) {
		String []tmpVector = linha.split("	");
		Email tmpEmail = new Email(tmpVector[0], tipo);
		for(int i=1;i<tmpVector.length;i++) {
			tmpEmail.adicionar_Regras(tmpVector[i]);
		}
		emails.add(tmpEmail);
	}

	public ArrayList<Email> get_Emails() {
		return emails;
	}
	
	public ArrayList<Rule> get_Regras(){
		return regras;
	}
}