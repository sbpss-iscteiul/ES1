package txtreader;

import java.util.ArrayList;

public class Email {
	
	private String emailName;
	private ArrayList<Rule> rules;
	
	public Email(String nomeEmail) {
		this.emailName=nomeEmail;
		this.rules=new ArrayList<Rule>();
	}
	
	
	public void setRules(ArrayList<Rule> rules) {
		this.rules = rules;
	}


	public void add_Rules(Rule Rule) {
		rules.add(Rule);
	}

	public String getEmailName() {
		return emailName;
	}

	public ArrayList<Rule> getRules() {
		return rules;
	}
	
	
	
	@Override
	public String toString() {
		return "Email name:"+emailName;
	}
}