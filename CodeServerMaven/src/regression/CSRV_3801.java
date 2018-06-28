package regression;

import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import base.BaseClass;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CSRV_3801 extends BaseClass{

	@Test
	public void VeirfyValidFileResponse(){
		URL=EA_URL+"/api/v2/files/commits";
		RestAssured.baseURI = URL;
		RequestSpecification httpRequest = RestAssured.given();
		httpRequest.queryParam("dfScmUrl", "https://github.com/yasserg/crawler4j.git?branch=master");
		httpRequest.queryParam("filePaths", "crawler4j/src/main/java/edu/uci/ics/crawler4j/crawler/Configurable.java");
		httpRequest.queryParam("startRevision", "c62179b7a1f6cfb49e503ca52cdceed9cf4131b4");
		Response response = httpRequest.request(Method.GET);
		Assert.assertEquals(response.getStatusCode(), 200,"Valid Response Returned");
		if(response.getStatusCode()==200){
			
		
			String responseBody = response.getBody().asString();
	
			
	
			//List<List<String>> content= jsonPathEvaluator.getList("contents");
	
			List<Map<String, Object>> contents = response.jsonPath().getList("contents");
			Reporter.log("Files: "+contents.size());
			Reporter.log("<br>");
			System.out.println("Response Body is: " + responseBody);
			Assert.assertEquals(contents.size(), 10);
			for (int i=0;i<contents.size();i++){
	
				int id=(int) contents.get(i).get("id");
				Reporter.log("id: "+id);
				Reporter.log("rev: "+contents.get(i).get("rev"));
				Reporter.log("shortRev: "+contents.get(i).get("shortRev"));
				Reporter.log("parentsIds: "+contents.get(i).get("parentsIds"));
				Reporter.log("commitTime: "+contents.get(i).get("commitTime"));
				int loc=(int) contents.get(i).get("linesOfCode");
				Reporter.log("linesOfCode: "+loc);
				Reporter.log("<br>");
			}
		}
	}
	@Test
	public void VeirfyRequiredFields(){
		URL=EA_URL+"/api/v2/files/commits";
		RestAssured.baseURI = URL;
		RequestSpecification httpRequest = RestAssured.given();
		/*httpRequest.queryParam("dfScmUrl", "https://github.com/yasserg/crawler4j.git?branch=master");
		httpRequest.queryParam("filePaths", "crawler4j/src/main/java/edu/uci/ics/crawler4j/crawler/Configurable.java");
		httpRequest.queryParam("startRevision", "c62179b7a1f6cfb49e503ca52cdceed9cf4131b4");*/
		Response response = httpRequest.request(Method.GET);
		Assert.assertEquals(response.getStatusCode(), 400,"Bad Request");
		String responseBody = response.getBody().asString();
		Reporter.log("Response Body is: " + responseBody);
	}
	
}