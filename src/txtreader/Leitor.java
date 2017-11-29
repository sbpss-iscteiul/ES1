package txtreader;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

import txtreader.rule;

public class leitor {
	
	private ArrayList<rule>regras;
	private ArrayList<Email>emails;
	
	public leitor() {
		regras=new ArrayList<rule>();
		emails=new ArrayList<Email>();
	}
	
	public void ler_Regras() {
		try {
			Scanner sc = new Scanner(new File("C:\\Users\\Sergio-PC\\Desktop\\Universidade\\Engenharia de Software\\Projecto\\Inputs\\rules.cf"));
			int contador=0;
			String linha="";
			double peso=0.0;
			while(sc.hasNextLine()) {
				linha=sc.nextLine();
				peso=(10*Math.random())-5;
				regras.add(new rule(linha, peso));
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
		Email tmpEmail = new Email(tmpVector[0], tipo);
		for(int i=1;i<tmpVector.length;i++) {
			tmpEmail.adicionar_Regras(tmpVector[i]);
		}
		emails.add(tmpEmail);
	}

	public ArrayList<Email> get_Emails() {
		return emails;
	}
	
	public ArrayList<rule> get_Regras(){
		return regras;
	}
	
	public void avaliar() {
		Email correio=null;
		for (int i=0;i<5;i++) {
			correio= emails.get(i);
			avaliar_regras(correio);
		}
	}
	
	private void avaliar_regras(Email mail) {
		double contador=0.0;
		//contem as regras do emails que recebe como input
		ArrayList<String>rules=mail.getRegras();
		//contem os nomes das regras a lista grande de regras
		ArrayList<String> regrasNames = new ArrayList<>();
		//coloca todos os nomes das regras dentro do array 'regrasNomes'
		for(int i=0;i<regras.size();i++) {
			regrasNames.add(regras.get(i).getName());
		}
		//vai ver se a nome da regra esta contido na lista de nomes de regras
		for(int j=0;j<rules.size();j++) {
			if(regrasNames.contains(rules.get(j)))
				contador+=regras.get(j).getPeso();
		}
		System.out.println(contador+" "+mail.getTipo());
		if(contador>=5.0) {
			if(mail.getTipo().equals(type.SPAM))
				System.out.println("Bem avaliado");
			else
				System.out.println("Falso Negativo");
			System.out.println("Este email é spam");
		}else{
			if(mail.getTipo().equals(type.HAM))
				System.out.println("Bem avaliado");
			else
				System.out.println("Falso Positivo");
			System.out.println("Este email é spam");
		}
			
	}
}