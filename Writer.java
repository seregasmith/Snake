package Snake;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class Writer {
	private PrintWriter writer;
	private Map<String,Integer> strings = new HashMap<String, Integer>();
	public Writer(String file_path) throws IOException {
		try {
			writer = new PrintWriter(new FileWriter(file_path,true));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void write(String src){
		writer.println(src);
		writer.flush();
	}
}
