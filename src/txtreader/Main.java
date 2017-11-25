package txtreader;

public class Main {
	public static void main(String[] args) {
		leitor vailer =new leitor();
		vailer.ler_Regras();
		vailer.ler_emails("ham.log");
		vailer.ler_emails("spam.log");
		vailer.imprimir_resultados();
	}
}
