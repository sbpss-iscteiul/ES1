package txtreader;

import java.util.ArrayList;

public class Email {
	
	private String emailName;
	private ArrayList<String> regras;
	private type tipo;
	
	public Email(String nomeEmail, type tipo) {
		this.emailName=nomeEmail;
		this.regras=new ArrayList<String>();
		this.tipo=tipo;
	}
	
	public void adicionar_Regras(String Rule) {
		regras.add(Rule);
	}

	public String getEmailName() {
		return emailName;
	}

	public ArrayList<String> getRegras() {
		return regras;
	}

	public type getTipo() {
		return tipo;
	}

	public void setTipo(type tipo) {
		this.tipo = tipo;
	}
	
	@Override
	public String toString() {
		return "Email name: "+emailName+"Email rules: "+regras;
	}
}