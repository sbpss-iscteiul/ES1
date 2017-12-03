package txtreader;


public class MainReader {
	public static void main(String[] args) {
		Leitor vailer =new Leitor();

		vailer.ler_Regras("caminho do rules.cf");
		vailer.write_Rules("caminho da nova pasta o");
		//colocar localiza��o o ficheiro em ambos os "ler_emails"
	}
}
