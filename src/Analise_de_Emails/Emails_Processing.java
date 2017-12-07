package Analise_de_Emails;

import java.util.ArrayList;

import txtreader.*;

public class Emails_Processing {
	
	private Leitor email_reader;
	private int falsoPositivo;
	private int falsoNegativo;
	//Evaluate[0]->Falso negativo
	//Evaluate[1]->Falso Positivo
	
	
	
	
	
	
	public Emails_Processing(String sourceham,String sourcespam,String sourcerules) {
		email_reader=new Leitor();
		email_reader.ler_Regras(sourcerules);
		email_reader.ler_emails(sourceham);
		email_reader.ler_emails(sourcespam);
		falsoNegativo=0;
		falsoPositivo=0;
		
	}
	
	
	
	public void avaliar() {
//		falsoNegativo=0;
//		falsoPositivo=0;
		Email correio=null;
		for (int i=0;i<email_reader.get_Emails().size();i++) {
			correio= email_reader.get_Emails().get(i);
			avaliar_regras(correio);
		}
		System.out.println("Este conjunto de emails tem "+falsoNegativo+" falsos negativos e "+falsoPositivo+" falsos positivos");
		
	}
	
	private void avaliar_regras(Email mail) {
		double somatorio_de_regras=0.0;
		//contem as regras do emails que recebe como input
		ArrayList<String>rules_email_input=mail.getRegras();
		//contem os nomes das regras a lista grande de regras
		ArrayList<String> regrasNames = new ArrayList<>();
		//coloca todos os nomes das regras dentro do array 'regrasNomes'
		for(int i=0;i<email_reader.get_Regras().size();i++) {
			regrasNames.add(email_reader.get_Regras().get(i).getName());
		}
		//vai ver se o nome da regra esta contido na lista de nomes de regras
		for(int j=0;j<rules_email_input.size();j++) {
			if(regrasNames.contains(rules_email_input.get(j)))
				somatorio_de_regras+=email_reader.get_Regras().get(j).getPeso();
		}
		if(somatorio_de_regras>=5.0) {
			if(mail.getTipo().equals(Type.HAM))
				falsoPositivo++;
		}else{
			if(mail.getTipo().equals(Type.SPAM))
				falsoNegativo++;
		}
	}
	
	
}
