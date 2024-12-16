package apiscripts;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import generic.Freamework_Constants;
import generic.Spec_builder;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import login_Pojo.Serialization;
import utils.Get_Data_propertyFile;

public class login_shopper implements Freamework_Constants {
	@Test
	public void logic() throws JsonProcessingException
	{
		Get_Data_propertyFile gp=new Get_Data_propertyFile();
		String url = gp.Get_data("baseurl");
		String endpoint = gp.Get_data("login_shopper_ep");
		String content = gp.Get_data("content_type");
		String email = gp.Get_data("email");
		
		Serialization s=new Serialization();
		s.setEmail(email);
		s.setPassword("Nav@123s");
		s.setRole("SHOPPER");
		 
		ObjectMapper obj=new ObjectMapper();
		String jsonbody = obj.writeValueAsString(s);
		
		Spec_builder sp=new Spec_builder();
		RequestSpecification request = sp.request(url, jsonbody, content);
		ResponseSpecification response = sp.response(OK, "message", "OK", content, time, StatusOk);
		RestAssured.given().spec(request).when().post(endpoint).then().spec(response).extract().response();
		
	}

}
