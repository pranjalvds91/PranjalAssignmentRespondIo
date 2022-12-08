package stepDefinition;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.messages.internal.com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;



public class addBooksStepDefination{
	public Scenario scenario;
	
	 @Before
     public Scenario start(Scenario sc) {
     	this.scenario = sc;
  		
 	     return scenario;
     	
     }
	
	Map<String, String> passData = new HashMap<String, String>();
	
	
	
	
	@Given("^User enter the URL of adding book$")
	public void user_enter_the_URL_of_adding_book() {
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		
		scenario.write("https://restful-booker.herokuapp.com");
	}

	@When("^User enter the details of book$")
	public void user_enter_the_details_of_book() 
	{
		
		File jsonDataInFile = new File("src/test/resources/TestData/bookDetails.json");
		String apiResponse = given().header("Content-Type","application/json").body(jsonDataInFile).when()
				.post("/booking").then().assertThat().statusCode(200).extract().response().asString();
				JsonPath myjsonpath = new JsonPath(apiResponse);
				String getBookid = myjsonpath.getString("bookingid");
				passData.put("BookingId", getBookid);
				
				scenario.write("doing booking of hotels"+ apiResponse);
				scenario.write("the created booking id is"+ getBookid);
}

	@Then("^User validate the status of book added$")
	public void user_validate_the_status_of_book_added() {
		
	String getData = passData.get("BookingId");
	  
	String responseGet = given().when().get("booking/"+getData+"").
				then().statusCode(200).extract().response().asString();
	
	   scenario.write("Validating the response"+ responseGet);
		scenario.write("the created booking id is"+ getData);
	   
	}
	
	@When("^User enter the details of book Without Addtional needs$")
	public void user_enter_the_details_of_book_Without_Addtional_needs() {
	   
		File jsonDataInFile = new File("src/test/resources/TestData/noAddtional.json");
		String apiResponse = given().header("Content-Type","application/json").body(jsonDataInFile).when()
				.post("/booking").then().assertThat().statusCode(200).extract().response().asString();
				JsonPath myjsonpath = new JsonPath(apiResponse);
				String getBookid = myjsonpath.getString("bookingid");
				passData.put("BookingId", getBookid);
				
				scenario.write("doing booking of hotels without addtional needs"+ apiResponse);
				scenario.write("the created booking id is"+ getBookid);
	}

	@Then("^User validate the status of book added Without Addtional needs$")
	public void user_validate_the_status_of_book_added_Without_Addtional_needs() {
		  
		   
		   String getData = passData.get("BookingId");
		   System.out.println("The id from maps is" +getData);
		   
		   String responseGet = given().when().get("booking/"+getData+"").
					then().statusCode(200).extract().response().asString();
		   scenario.write("Validating the response Without Addtional needs"+ responseGet);
			scenario.write("the created booking id is"+ getData);
	}

	@When("^User enter the details of book With Old Dates$")
	public void user_enter_the_details_of_book_With_Old_Dates() {
		File jsonDataInFile = new File("src/test/resources/TestData/oldDates.json");
		String apiResponse = given().header("Content-Type","application/json").body(jsonDataInFile).when()
				.post("/booking").then().assertThat().statusCode(200).extract().response().asString();
		      
		
		        scenario.write("Doing hotesl booking With Old Dates"+ apiResponse);
		
				JsonPath myjsonpath = new JsonPath(apiResponse);
				String getBookid = myjsonpath.getString("bookingid");
				String checkinvalue1 = myjsonpath.getString("booking.bookingdates.checkin");
				System.out.println(checkinvalue1);
				String checkoutvalue1 = myjsonpath.getString("booking.bookingdates.checkout");
				System.out.println(checkoutvalue1);
				
				
				scenario.write("doing booking of hotels with Old dates"+ apiResponse);
				scenario.write("the booking id is"+ getBookid);
				scenario.write("the checkindate is"+ checkinvalue1);
				scenario.write("the checkout date is"+ checkoutvalue1);
				

			     passData.put("oldcheckoutdate",checkoutvalue1);
			     passData.put("oldcheckindate",checkinvalue1);
				 passData.put("BookingId", getBookid);
	}
	

	@Then("^User validate the status of book added With Old Dates$")
	public void user_validate_the_status_of_book_added_With_Old_Dates() {
		String getData = passData.get("BookingId");
		String getData1 = passData.get("oldcheckoutdate");
		String getData2 = passData.get("oldcheckindate");
		   System.out.println("The id from maps is" +getData2);
		   System.out.println("The id from maps is" +getData);
		   System.out.println("The id from maps is" +getData);
		   
		   String responseGet = given().when().get("booking/"+getData+"").
				then().statusCode(200).extract().response().asString();
		   System.out.println("Final Response is " +responseGet);
		   
		   
		   JsonPath myjsonpath = new JsonPath(responseGet);
			String getBookid = myjsonpath.getString("bookingid");
			String checkinvalue1 = myjsonpath.getString("bookingdates.checkin");
			System.out.println(checkinvalue1);
			String checkoutvalue1 = myjsonpath.getString("bookingdates.checkout");
			System.out.println(checkoutvalue1);
			
			scenario.write("Valiadting booking of hotels with Old dates"+ responseGet);
			scenario.write("the booking id is"+ getBookid);
			scenario.write("the checkindate is"+ checkinvalue1);
			scenario.write("the checkout date is"+ checkoutvalue1);
			
			
			Assert.assertTrue("the checkin date is not same", getData1.equalsIgnoreCase(checkoutvalue1));
			
			Assert.assertTrue("the checkout date is not same", getData2.equalsIgnoreCase(checkinvalue1));
	}

	@When("^User enter the details of book with Blank Checkout Date and Checkin Date$")
	public void user_enter_the_details_of_book_with_Blank_Checkout_Date_and_Checkin_Date() {
	    // Write code here that turns the phrase above into concrete actions
		File jsonDataInFile = new File("src/test/resources/TestData/noDates.json");
		String apiResponse = given().header("Content-Type","application/json").body(jsonDataInFile).when()
				.post("/booking").then().assertThat().statusCode(200).extract().response().asString();
				JsonPath myjsonpath = new JsonPath(apiResponse);
				String getBookid = myjsonpath.getString("bookingid");
				passData.put("BookingId", getBookid);
				
				scenario.write("Valiadting booking of hotels with Blank Checkout Date and Checkin Date"+ apiResponse);
	}

	@Then("^User validate the status of book added with Blank Checkout Date and Checkin Date$")
	public void user_validate_the_status_of_book_added_with_Blank_Checkout_Date_and_Checkin_Date() {
	    // Write code here that turns the phrase above into concrete actions
		System.out.println("validation");
		   
		   String getData = passData.get("BookingId");
		   System.out.println("The id from maps is" +getData);
		   
		   String responseGet = given().when().get("booking/"+getData+"").
					then().statusCode(200).extract().response().asString();
		   System.out.println("Final Response is " +responseGet);
		   
		   scenario.write("Validating the response with Blank Checkout Date and Checkin Date"+ responseGet);
	}

	@When("^User enter the details of book with Blank details of Both First and Last Name$")
	public void user_enter_the_details_of_book_with_Blank_details_of_Both_First_and_Last_Name() {
	    // Write code here that turns the phrase above into concrete actions
		File jsonDataInFile = new File("src/test/resources/TestData/noNames.json");
		String apiResponse = given().header("Content-Type","application/json").body(jsonDataInFile).when()
				.post("/booking").then().assertThat().statusCode(200).extract().response().asString();
				JsonPath myjsonpath = new JsonPath(apiResponse);
				String getBookid = myjsonpath.getString("bookingid");
				passData.put("BookingId", getBookid);
	}

	@Then("^User validate the status of book added with Blank details of Both First and Last Name$")
	public void user_validate_the_status_of_book_added_with_Blank_details_of_Both_First_and_Last_Name() {
		System.out.println("validation");
		   
		   String getData = passData.get("BookingId");
		   System.out.println("The id from maps is" +getData);
		   
		   String responseGet = given().when().get("booking/"+getData+"").
					then().statusCode(200).extract().response().asString();
		   System.out.println("Final Response is " +responseGet);
		   
		   scenario.write("Validating the response With Blank details of Both First and Last Name"+ responseGet);
	}

	@When("^User enter the details of book With Negative money value$")
	public void user_enter_the_details_of_book_With_Negative_money_value() {
		File jsonDataInFile = new File("src/test/resources/TestData/negativePrice.json");
		String apiResponse = given().header("Content-Type","application/json").body(jsonDataInFile).when()
				.post("/booking").then().assertThat().statusCode(200).extract().response().asString();
				JsonPath myjsonpath = new JsonPath(apiResponse);
				String getBookid = myjsonpath.getString("bookingid");
				passData.put("BookingId", getBookid);
	}

	@Then("^User validate the status of book added With Negative money value$")
	public void user_validate_the_status_of_book_added_With_Negative_money_value() {
		System.out.println("validation");
		   
		   String getData = passData.get("BookingId");
		   System.out.println("The id from maps is" +getData);
		   
		   String responseGet = given().when().get("booking/"+getData+"").
					then().statusCode(200).extract().response().asString();
		   System.out.println("Final Response is " +responseGet);
		   
		   scenario.write("Validating the response  With Negative money value"+ responseGet);
	}
	
	
	@After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
        	scenario.write("Scenario is failed");
         
            Assert.fail("Test case is failed");
         
        }
        scenario.write("scenario is finished");
        scenario.write("new scenario is started");
        
    }

}

