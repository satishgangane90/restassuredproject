
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.Assert;

import Files.Payload;
import Files.ReusableMethods;

public class Basics {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		// Given : All input details
		// When - Submit the API - resource,http method
		// Then - Validate the response
		//content of file convert to byte & then byte to string

		RestAssured.baseURI = "https://rahulshettyacademy.com";

		String resp = given().log().all().queryParam("Key", "qaclick123").header("content-type", "application/json")
				.body(Files.readAllBytes(Paths.get("/Users/satish/Downloads/addplaceAPI.json"))).when().post("maps/api/place/add/json")
				.then().assertThat().statusCode(200).body("scope", equalTo("APP"))
				.header("server", "Apache/2.4.18 (Ubuntu)").extract().response().asString();

		System.out.println(resp);

		JsonPath js = new JsonPath(resp); // for parsed value

		String Pid = js.getString("place_id");

		System.out.println("Place_id :" +Pid);

		// Add Place -- Update Place with new Address -- Get place to verify new address
		// in response

		String Nad = "59 Oval South Africa";

		given().log().all().queryParam("Key", "qaclick123").header("content-type", "application/json")
				.body("{\n" + "\"place_id\":\"" + Pid + "\",\n" + "\"address\":\"" + Nad + "\",\n"
						+ "\"key\":\"qaclick123\"\n" + "}")
				.when().put("maps/api/place/update/json").then().assertThat().log().all().statusCode(200)
				.body("msg", equalTo("Address successfully updated"));

		
		
		
	String getresp	= given().log().all().queryParam("place_id", Pid).queryParam("key", "qaclick123").when()
				.get("maps/api/place/get/json").then().assertThat().log().all().statusCode(200).extract().response().asString();
	
	 System.out.println(getresp);

	 
	
	 JsonPath js1 =  ReusableMethods.rawtojson(getresp);
	 
	 String actualaddress = js1.getString("address");
	 
	 System.out.println(actualaddress);
	 
	 Assert.assertEquals(actualaddress, Nad);
		

      /*
		JsonPath jss = new JsonPath(newresp);

		String actualaddress = jss.getString("address");
		
		
		System.out.println(actualaddress);

		Assert.assertEquals(actualaddress, Nad);

**/

	}

}
