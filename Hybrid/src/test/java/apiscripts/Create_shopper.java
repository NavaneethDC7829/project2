package apiscripts;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import createShopperPOJO.Serialization;
import generic.Freamework_Constants;
import generic.Spec_builder;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import utils.Get_Data_propertyFile;

public class Create_shopper implements Freamework_Constants {
	FileOutputStream fos;
	String jsonbody;
	@Test
	public void create()
	{
		String random = RandomStringUtils.randomAlphanumeric(5);
		String randomemail = random+"@gmail.com";
		Serialization s=new Serialization();
		s.setCity("Banglore");
		s.setCountry("India");
		s.setEmail(randomemail);
		s.setFirstName("navaneeth");
		s.setGender("MALE");
		s.setLastName("dcn");
		s.setPassword("Nav@123s");
		s.setPhone("9087654321");
		s.setState("Karnataka");
		s.setZoneId("ALPHA");
		ObjectMapper obj=new ObjectMapper();
		try {
			jsonbody = obj.writerWithDefaultPrettyPrinter().writeValueAsString(s);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Get_Data_propertyFile gp=new Get_Data_propertyFile();
		String url = gp.Get_data("baseurl");
		String content = gp.Get_data("content_type");
		String endpoint = gp.Get_data("Create_Shopper_EP");
		
		Spec_builder sp=new Spec_builder();
		RequestSpecification request = sp.request(url,jsonbody,content);
		 ResponseSpecification response = sp.response(Created, "message", "Created", content, time, statusline);
		
		Response res = RestAssured.given().spec(request).when().post(endpoint).then().spec(response).extract().response();
		 System.out.println(res.asPrettyString());
		 
		 JsonPath jp=new JsonPath(res.asPrettyString());
		 String shopperId = jp.getString("data.userId");
		 String email = jp.getString("data.email");
		  try {
			fos=new FileOutputStream(property_path1,true);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
		  Properties p=new Properties();
		  p.setProperty("ShopperId", shopperId);
		  p.setProperty("email", email);
		  
		  try {
			p.store(fos, "store the data");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	}

}
