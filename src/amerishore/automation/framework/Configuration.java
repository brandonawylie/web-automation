package amerishore.automation.framework;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Configuration {
	private final String SEPERATOR = ":";
	private final String START_TYPE = "{";
	private final String END_TYPE = ";}";
	private final String END_SUBTYPE = ";";

	/*
	 * A map containing the true values of placeholders following the {{placeholder}} pattern
	 */
	Map<String, ArrayList<String>> values = new HashMap<String, ArrayList<String>>();

	public boolean loadConfigurationFile(File f) throws FileNotFoundException{
		BufferedReader reader = new BufferedReader(new FileReader(f));
		String total = null;
		String input = null;
		try {
			while((input = reader.readLine()) != null){
				total += input;
			}
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

		/*
		 * example configuration file:
		 * #values
		 *  { Urls : "google.com", "amerishore.com";
		 *    Users: "brandon@amerishore.com";
		 *    passwords: "password";}
		 *
		 * #presets
		 *  { ... ;}
		 */
		String[] types = total.split(";}");
		//assign the stuffs
		for(int i = 0; i < types.length; i++){
			String currentType = types[i];
			//values
			if(i == 0){
				String tmp = currentType.substring(currentType.indexOf("{"));
				while(tmp.contains(SEPERATOR)){
					/*
					 * get the title and definitions
					 */
					String title = tmp.substring(0, tmp.indexOf(SEPERATOR));
					String definitions = tmp.substring(tmp.indexOf(SEPERATOR), tmp.indexOf(";"));
					/*
					 * split the definitions and parse through the quotes on each one and add it to the values array.
					 */
					String[] defArr = definitions.split(",");
					for(int j = 0; j < defArr.length; j++){
						if(!values.containsKey(title)){
							values.put(title, new ArrayList<String>());
						}
						String temp = defArr[i].substring(defArr[i].indexOf("\""));
						temp = temp.substring(0, temp.indexOf("\""));
						values.get(title).add(temp);
					}
				}
			}
		}
		return true;
	}
}
