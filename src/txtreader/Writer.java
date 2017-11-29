package txtreader;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Writer {

	private File file;
	private ArrayList<Rule> fileList;
	private FileWriter fileWriter;
	
	public Writer(ArrayList<Rule> fileList){
		this.fileList = fileList;
	}
	
	public void write(){
		file = new File("src/Documents/rulesTest.cf");
		try {
			fileWriter = new FileWriter(file);
			for(int i=0; i<fileList.size(); i++){
				fileWriter.write(fileList.get(i)+"\n");
			}
			fileWriter.flush();
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
