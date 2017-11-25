package txtreader;

public class rule {
	
	private String Name;
	private double Peso;
	
	public rule(String name, double peso){
		this.Name=name;
		this.Peso=peso;
	}
	
	@Override
	public String toString() {
		String tostring= "Regra: "+this.Name+" ;Peso: "+this.Peso;
		return tostring;
	}
}

