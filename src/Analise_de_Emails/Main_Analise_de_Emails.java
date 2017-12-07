package Analise_de_Emails;

public class Main_Analise_de_Emails {
	public static void main(String[] args) {
		Emails_Processing tmp = new Emails_Processing("/Users/mohammadmudassir/Desktop/PI_ficheiros/ham.log","/Users/mohammadmudassir/Desktop/PI_ficheiros/spam.log", "/Users/mohammadmudassir/Desktop/PI_ficheiros/rules.cf");
		tmp.avaliar();
	}
}
