package txtreader;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Writer {

	private File file;
	private ArrayList<Rule> ruleList;
	private FileWriter fileWriter;
	
	public Writer(ArrayList<Rule> ruleList){
		this.ruleList = ruleList;
	}
	
	public void write(){
		file = new File("src/Documents/rulesTest.cf");
		try {
			fileWriter = new FileWriter(file);
			for(int i=0; i<ruleList.size(); i++){
				fileWriter.write(ruleList.get(i)+"\n");
			}
			fileWriter.flush();
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void addRule(Rule rule){
		ruleList.add(rule);
	}
	
}
