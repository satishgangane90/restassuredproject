package Files;

import io.restassured.path.json.JsonPath;

public class ReusableMethods {

	public static JsonPath rawtojson(String getresp) {
		
		
		JsonPath js = new JsonPath(getresp);
		
		return js;
		
	}
	
	
	public static int  purchaseamt(int price , int copies) {
		
		int pamt = price * copies;
		
		return pamt; 
	}
	
	
}
