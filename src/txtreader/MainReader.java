package txtreader;

public class MainReader {
	public static void main(String[] args) {
		Leitor vailer =new Leitor();
		vailer.ler_Regras();
		vailer.ler_emails("ham.log");
		vailer.ler_emails("spam.log");
		vailer.imprimir_resultados();
	}
}
