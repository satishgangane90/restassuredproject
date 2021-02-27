package Files;


import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class DynamicJson {
	
	
	@Test(dataProvider="Booksdata")
    public void addbook(String isbn,String aisle) {
		
		RestAssured.baseURI = "http://216.10.245.166";
		
		String response = given().log().all().header("Content-Type","application/json").body(Payload.adbook(isbn,aisle))
		.when()
		.post("/Library/Addbook.php")
		.then().assertThat().statusCode(200)
		.extract().response().asString();
		
		JsonPath js = ReusableMethods.rawtojson(response);
		
	String ID =	js.get("ID");
	
	System.out.println(ID);
	
	
	//deltebook
	
		
	}
	@DataProvider(name="Booksdata")
	public Object[][] getdata() {
		
		
		return new Object[][] {{"weqeqeqeq","8766"}, {"ccvccvvvvvc","98765555"}, {"nbnbnbnbnb","54542334"}};
		
		
		
		
		
	}
	
	
	
}
