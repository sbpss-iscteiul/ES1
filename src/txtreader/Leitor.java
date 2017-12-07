package txtreader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
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
			String ruleName="";
			double peso=0.0;
			while(sc.hasNext()) {
				ruleName=sc.next();
				if(sc.hasNextDouble()){
					peso=sc.nextDouble();
				}
				else{
					peso=0.0;
				}
				Rule aux = new Rule(ruleName, peso);
				regras.add(aux);
//				System.out.println(aux);
			}
			sc.close();
		} catch (FileNotFoundException e) {
			System.out.println("Ficheiro n�o foi encontrado");
		}
	}
	public void write_Rules(String source){
		PrintWriter pw;
		try {
			pw = new PrintWriter(new FileOutputStream(new File(source)));
			pw.flush();
			for(Rule e : regras){
				pw.println(e.getName() +" "+ e.getPeso());
			}
			pw.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	
	public void ler_emails(String fonte) {
		try {
			String source=fonte;
			Type tipo = type_definition(source);
			System.out.println(tipo);
			String linha="";
			Scanner sc = new Scanner(new File(source));
			while(sc.hasNextLine()) {
				linha=sc.nextLine();
				tratar_email(linha, tipo);
			}
			sc.close();
		} catch (FileNotFoundException e) {
			System.out.println("Ficheiro n�o foi encontrado");
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
		if(source.contains("ham.log"))
			tipo=Type.HAM;
		else if(source.contains("spam.log"))
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