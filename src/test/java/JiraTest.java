
import static io.restassured.RestAssured.*;

import java.io.File;

import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;


public class JiraTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		RestAssured.baseURI= "http://localhost:8080";
		
		SessionFilter session = new SessionFilter();
		
		//http handling when handling online methods.  given().relaxedHTTPSValidation()
		
		
		
		 given().log().all().header("Content-Type","application/json").body("{ \"username\": \"TestSatish\", \"password\": \"sat140690\" }")
		.log().all().filter(session).when().post("/rest/auth/1/session").then().assertThat().statusCode(200).extract().response();
		
		
		
		String expectedmessage = "this is expected message ";
		
		
		//Add comment 
	String comntresponse =	given().pathParam("ID", "10026").log().all().header("Content-Type","application/json").body("{\n"
				+ "    \"body\": \""+expectedmessage+"\",\n"
				+ "    \"visibility\": {\n"
				+ "        \"type\": \"role\",\n"
				+ "        \"value\": \"Administrators\"\n"
				+ "    }\n"
				+ "}").filter(session).when().post("/rest/api/2/issue/{ID}/comment").then().log().all().extract().response().asString();
		
		
	JsonPath js = new JsonPath(comntresponse);
	
	String CommentID =	js.getString("ID");
		
		
		//Add Attachment
		
		given().header("X-Atlassian-Token", "no-check").filter(session).pathParam("ID", "10026").header("Content-Type","multipart/form-data").multiPart("file", new File("jira.txt")).when().post("/rest/api/2/issue/{ID}/attachments").then().log().all().assertThat().statusCode(200);
		
		
		//get issue
		
		String issuedetails =given().log().all().filter(session).pathParam("ID", "10026").queryParam("fields", "comment").get("/rest/api/2/issue/{ID}").then().log().all().extract().response().asString();

		
		System.out.println(issuedetails);
		
		JsonPath js1 = new JsonPath(issuedetails);
		
		int size = js1.get("fields.comment.comments.size()");
		
		for(int i=0;i<size;i++) {
			
	String Commentidissue =	js1.get("fields.comment.comments["+i+"].id").toString();
			
			if(Commentidissue.equalsIgnoreCase(CommentID)) {
				
				String bodymessage = js1.get("fields.comment.comments["+i+"].body").toString();
				
				System.out.println(bodymessage);
				
				Assert.assertEquals(bodymessage, expectedmessage);
				
			}
		
			
		}
		
	}

}
