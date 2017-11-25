package txtreader;

public class Main {
	public static void main(String[] args) {
		leitor vailer =new leitor();
		vailer.ler_Regras();
		vailer.ler_emails("C:\\Users\\Sergio-PC\\Desktop\\Universidade\\Engenharia de Software\\Projecto\\Inputs\\ham.log");
		vailer.ler_emails("C:\\Users\\Sergio-PC\\Desktop\\Universidade\\Engenharia de Software\\Projecto\\Inputs\\spam.log");
		vailer.avaliar();
	}
}
