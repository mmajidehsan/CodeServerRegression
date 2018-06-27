package regression;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseClass;
import utilities.PackageProperties;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CSRV_3422 extends BaseClass{
  
	//Verify API is returning valid response

	@Test
	public void TestCase1() throws IOException {
		URL=FW_URL+"/api/v2/user/tickets/";
		System.out.println(URL);
		RestAssured.baseURI = URL;
		RequestSpecification Request = RestAssured.given();
		Request.contentType("application/json");



		Request.header("Authorization","Bearer "+Token);
		Response response = Request.request(Method.GET,"19/url");

		Assert.assertEquals(response.getStatusCode(), 200);

		JsonPath jsonPathEvaluator = response.jsonPath();
		//List<List<String>> content= jsonPathEvaluator.getList("contents");
		String url= jsonPathEvaluator.get("url");
		System.out.println("Ticket URL: "+url);
	
	
	}
	@Test
	public void TestCase2() throws IOException{
		URL=EA_URL+"/api/v2/issues";
		RestAssured.baseURI = URL;
		RequestSpecification Request = RestAssured.given();
		Request.contentType("application/json");

		String email=PackageProperties.GetProperty("From");

		Request.header("From",email);
		Response response = Request.request(Method.GET,"/8/url");

		Assert.assertEquals(response.getStatusCode(), 200);

		JsonPath jsonPathEvaluator = response.jsonPath();
		//List<List<String>> content= jsonPathEvaluator.getList("contents");
		String url= jsonPathEvaluator.get("url");
		System.out.println("Ticket URL: "+url);
	}
}
