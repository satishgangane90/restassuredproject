import Files.Payload;
import Files.ReusableMethods;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	
	
	 JsonPath js1 = new JsonPath(Payload.courseprice());
	 
	int count = js1.getInt("courses.size()");
	
	int PurchaseAmount =0;
	
	
	
	//System.out.println(count);
	
	int PurchaseAmt=   js1.getInt("dashboard.purchaseAmount");
	 
	//System.out.println(PurchaseAmt);
	
	String titlefirst = js1.get("courses[0].title");
	
	//System.out.println(titlefirst);
	
	String titlethired = js1.get("courses[3].title");   
	
	//System.out.println(titlethired);
	
	
	for (int i =0; i <count; i++) {
		
		
		
		String CheckTitle ="RPA";
		
		String Titles = js1.get("courses["+i+"].title");
			
	    int prices =  js1.get("courses["+i+"].price");
	 
	    int Copies =  js1.get("courses["+i+"].copies");
	    
	   // System.out.println("***************************");
	    
	    
	    PurchaseAmount = PurchaseAmount + ReusableMethods.purchaseamt(prices, Copies);
	    
		
	 /*System.out.println(Titles+"  "+prices);
	 
	 if(Titles.equals(CheckTitle)) {
		 
		 System.out.println(Titles+"  "+prices+" "+Copies);
	 }
	 */
	 
	
	 
	 
	 
	 
		
	}
	
	System.out.println("Final Amount of Purchase : "+PurchaseAmount);
	

}

	
}
	
	