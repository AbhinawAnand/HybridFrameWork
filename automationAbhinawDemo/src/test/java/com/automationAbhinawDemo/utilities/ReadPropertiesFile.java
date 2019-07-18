package com.automationAbhinawDemo.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.testng.annotations.Parameters;

public class ReadPropertiesFile {
	
	Properties prop;
	
	public ReadPropertiesFile() {
		File prop_file = new File("./config\\testconfig.properties");
		try {
			FileInputStream fis = new FileInputStream(prop_file);
			prop = new Properties();
			prop.load(fis);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			// TODO: handle exception
		}
	}
	
	public String getPropertyFileValue(String key_value){
		String val = prop.getProperty(key_value);
		return val;
		
	}

}
