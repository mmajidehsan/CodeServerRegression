package regression;

import java.io.IOException;
import java.util.List;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import base.BaseClass;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class CSRV_3847 extends BaseClass{
	
	//Verify API returns list that exceeds 2000 files that match defined regular expression
  @Test
  public void TestCase1() throws IOException {
	  URL=FW_URL+"/api/v2/commits/files";
		System.out.println(URL);
	  RestAssured.baseURI = URL;
		RequestSpecification httpRequest = RestAssured.given();
		httpRequest.queryParam("dfScmUrl", "https://github.com/ponsonio-aurea/FIRE-1121-INTBRP-DEMO.git?branch=master&rev=2dbc61fc54fb581547134d61b6bade19d99e4d8c");
		httpRequest.queryParam("regExp", "^.*.(.*)$");
		
		Response response = httpRequest.request(Method.GET);
		
		JsonPath jsonEvaluator = response.jsonPath();
		int matched=jsonEvaluator.get("filesMatchingQuery");
		int total=jsonEvaluator.get("filesTotal");
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(matched, total);
		Assert.assertEquals(total>2000, true);
		List<String> content= jsonEvaluator.getList("contents");
		for(String con : content)
		{
			Reporter.log(con);
		}
		System.out.println(content.size());

  }
  //Verify API returns single selected file that match defined regular expression
  @Test
  public void TestCase2() throws IOException {
	  URL=FW_URL+"/api/v2/commits/files";
	  RestAssured.baseURI = URL;
		RequestSpecification httpRequest = RestAssured.given();
		httpRequest.queryParam("dfScmUrl", "https://github.com/ponsonio-aurea/FIRE-1121-INTBRP-DEMO.git?branch=master&rev=2dbc61fc54fb581547134d61b6bade19d99e4d8c");
		httpRequest.queryParam("regExp", "^ruby/jruby/tool/nailgun/README.(txt)$");
		
		Response response = httpRequest.request(Method.GET);
		
		JsonPath jsonEvaluator = response.jsonPath();
		int matched=jsonEvaluator.get("filesMatchingQuery");
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(matched==1, true);
		List<String> content= jsonEvaluator.getList("contents");
		for(String con : content)
		{
			Reporter.log(con);
		}
		System.out.println(content.size());

  }
}
