package Snake;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LeadersReader {
	private BufferedReader reader;
	private Map<String,Integer> strings = new HashMap<String, Integer>();
	public LeadersReader(String file_path) {
		// TODO Auto-generated constructor stub
		try {
			reader = new BufferedReader(new FileReader(file_path));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void read(){
		
        String line;
        try {
			while ((line = reader.readLine()) != null) {
				String[] words = line.split("\\s+", 2);
				if(words.length != 2)
					continue;
				String key = words[0];
				int val = Integer.parseInt(words[1]);
				if(strings.containsKey(key)){
					if(strings.get(key) < val)
						strings.replace(key, val);
				}else
					strings.put(key, val);
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public Map<String, Integer> getStrings() {
		return strings;		
	}
}
