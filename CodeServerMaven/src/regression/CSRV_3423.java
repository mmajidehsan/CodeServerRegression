package regression;

import java.io.IOException;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import base.BaseClass;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CSRV_3423 extends BaseClass{

	@Test
	public void VarifyValidResponse() throws IOException{
		URL=EA_URL+"/api/v2/issues";
		RestAssured.baseURI = URL;
		RequestSpecification Request = RestAssured.given();
		Request.contentType("application/json");

	

		Request.header("From",Email);
		Response response = Request.request(Method.GET,"/8/url");

		Assert.assertEquals(response.getStatusCode(), 200,"Valid Response Returned");

		JsonPath jsonPathEvaluator = response.jsonPath();
		//List<List<String>> content= jsonPathEvaluator.getList("contents");
		String url= jsonPathEvaluator.get("url");
		System.out.println("Ticket URL: "+url);
		Reporter.log("Ticket URL: "+url);
	}
}
