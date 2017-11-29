package txtreader;

public class Rule {
	
	private String Name;
	private double Peso;
	
	public Rule(String name, double peso){
		this.Name=name;
		this.Peso=peso;
	}
	
	public String getName(){
		return Name;
	}
	
	public double getPeso(){
		return Peso;
	}
	
	@Override
	public String toString() {
		String tostring=this.Name+"		"+this.Peso;
		return tostring;
	}
}

