package txtreader;

public class rule {
	
	private String Name;
	private double Peso;
	
	public rule(String name, double peso){
		this.Name=name;
		this.Peso=peso;
	}
	
	
	public double getPeso() {
		return Peso;
	}


	public void setPeso(double peso) {
		Peso = peso;
	}


	public String getName() {
		return Name;
	}


	@Override
	public String toString() {
		String tostring= "Regra: "+this.Name+" ;Peso: "+this.Peso;
		return tostring;
	}
}

