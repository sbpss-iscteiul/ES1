package txtreader;

public class Rule {
	
	private String Name;
	private int Id;
	
	public Rule(String name, int id){
		this.Name=name;
		this.Id=id;
	}
	
	public int getId() {
		return Id;
	}

	public String getName() {
		return Name;
	}

	@Override
	public String toString() {
		return "Nome:"+Name+" Id:"+Id;
	}
}
