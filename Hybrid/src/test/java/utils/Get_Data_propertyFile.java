package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.Test;

import generic.Freamework_Constants;

public class Get_Data_propertyFile implements Freamework_Constants{
	FileInputStream fis;
	@Test
	public String Get_data(String key)
	{
		try {
			 fis = new FileInputStream(property_path1);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Properties p=new Properties();
		try {
		 p.load(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String data=p.getProperty(key);
		return data;
		
				
	}
	

}
