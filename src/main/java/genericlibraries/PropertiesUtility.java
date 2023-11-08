package genericlibraries;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtility {
	private Properties property;
	/**
	 * This method is used to initialize properties file
	 * @param filePath
	 */
	
	
	public void propertiesInitialization(String filePath) {
		FileInputStream fis = null;
		try {
		fis=new FileInputStream(filePath);
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		
		property = new Properties();
		try {
			property.load(fis);
		}
		catch(IOException e) {
			e.printStackTrace();
			
		}
	}
	/**
	 * This Method is used to fetch the data from properties file
	 * @param key
	 * @return
	 */
	public String readFromProperties(String key) {
		return property.getProperty(key);
		
	}

}
