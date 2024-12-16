package generic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.hamcrest.Matchers;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Spec_builder {
	@BeforeMethod
	public RequestSpecification request( String url,String body,String content) {
		RequestSpecBuilder s=new RequestSpecBuilder();
		RequestSpecification req = s.setBaseUri(url).setBody(body).setContentType(content).build();
		return req;
		}
	@AfterMethod
	public ResponseSpecification response(int code,String key,String value,String content,long time,String line) {
		ResponseSpecBuilder s= new ResponseSpecBuilder();
		ResponseSpecification res = s.expectStatusCode(code).expectBody(key,Matchers.equalTo(value)).expectContentType(content).expectResponseTime(Matchers.lessThanOrEqualTo(time)).expectStatusLine(line).build();
		return res;	}
		
}
