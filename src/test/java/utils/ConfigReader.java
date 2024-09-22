package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
	
	 private Properties property;


	    public Properties initProperties() {

	        property = new Properties();
	        try {
	            FileInputStream ip = new FileInputStream("./src/test/resources/config/config.properties");
	            property.load(ip);

	        } catch (FileNotFoundException exception) {
	            exception.printStackTrace();
	        } catch (IOException exception) {
	            exception.printStackTrace();
	        }

	        return property;

	    }

	    public void setProperty(String key, String value) {
	        if (property == null) {
	            initProperties();
	        }
	        property.setProperty(key, value);
	        saveProperties();
	    }

	    private void saveProperties() {
	        try (FileOutputStream outputStream = new FileOutputStream("./src/test/resources/config/config.properties")) {
	            property.store(outputStream, null);
	        } catch (IOException exception) {
	            exception.printStackTrace();
	        }
	    }

	    public String getProperty(String key) {
	        if (property == null) {
	            initProperties();
	        }
	        return property.getProperty(key);
	    }
	    
	    
	    
	}




