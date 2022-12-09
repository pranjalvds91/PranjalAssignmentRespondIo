package stepDefinition;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.IpriceDress;
import pageObjects.IpriceHomePage;
import pageObjects.IpriceSearchPage;


public class scenarioOneDefination {

	
	
	public  WebDriver driver;
	public WebElement we;
    
	public Scenario scenario;
	IpriceHomePage ipage;
    IpriceDress idress;
    IpriceSearchPage isearch ;
    public static Map < String, String > passDataBetwenSteps = new HashMap < String, String > ();
    
    public  String getValueFromPropertyFile(String filepath, String passkeyname) 
	{
		String data = "";
	    FileReader reader = null;
		try {
			reader = new FileReader(filepath);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	      
	    Properties p=new Properties();  
	    try {
			p.load(reader);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	      
	   data = p.getProperty(passkeyname);
	  
		
		return data;
		
		
	}
    
    
 	
 
	
        @Before
        public Scenario start(Scenario sc) {
        	this.scenario = sc;
     		
    	     return scenario;
        	
        }
       
       
        
    	
    	


        @Given("^User is on iPriceHomePage$")

        public void User_Is_On_iPrice_HomePage() {
        	String getBrowsername = getValueFromPropertyFile("src\\main\\resources\\browserUrl.properties","browsername");
        	String projectpath= getValueFromPropertyFile("src\\main\\resources\\browserUrl.properties","projectpath");
        	projectpath = projectpath  + "\\BrowserDrivers\\ChromeDriver\\chromedriver.exe" ;
        	projectpath = projectpath.replaceAll("\\\\", "\\\\\\\\");
        			
    		System.out.println(getBrowsername);
    		if(getBrowsername.equalsIgnoreCase("chrome"))
    		{
    		System.setProperty("webdriver.chrome.driver", projectpath);
    		driver = new ChromeDriver();
    		driver.get("https://iprice.my/");
    		driver.manage().window().maximize();
    		
    		}
    		ipage = new IpriceHomePage(driver,scenario,we);
    	     idress = new IpriceDress(driver);
    	     isearch = new IpriceSearchPage(driver);
        	System.out.println("hello homepage");
           scenario.write("opening webpage");
                
               
        }

        @When("^User Navigate to the computing Laptop page$")
        public void user_Navigate_to_the_computing_laptop_page() {
        	    ipage.clickComputingPageLink();
                scenario.write("Navigated  to Laptop Page");

        }

        @And("^User Select brand of Laptops$")
        public void user_Select_brand_of_Laptop() {
        	ipage.clickToOpenOption();
        	ipage.giveDelay(6000);
        	ipage.clickToOptionDell();
        	scenario.write("Navigated  to Dell Informationpage Page");
//                
        }

        @Then("^Validate that the results returned from first page matches the selected brand$")
        public void validate_that_the_results_returned_from_page_matches_the_selected_brand() {
        	
        	ipage.giveDelay(6000);
        	List<String> alldata = new ArrayList<String>();
                alldata = ipage.getDataFromDellPage();
                scenario.write(alldata.toString());
              //  isearch.closewebpage();
        	

        }
        

        @When("^User Navigate to the Clothing Dress page$")
        public void user_Navigate_to_the_Clothing_Dress_page() {
        	
                idress.clickOnDressLink();
                idress.giveDelay(6000);
        }

        @When("^Click on Price sorting until the indicator indicates that the list is sorted by price in descending order$")
        public void click_on_Price_sorting_until_the_indicator_indicates_that_the_list_is_sorted_by_price_in_descending_order() {
                idress.clickOnPriceSortingButton();
                idress.giveDelay(6000);
                String price1 = idress.verifyingPriceAfterSorting();
                idress.giveDelay(6000);
                passDataBetwenSteps.put("oldprice", price1);
                System.out.println("out of 2nd Step");

        }

        @Then("^Validate that the results are sorted in descending order of Price$")
        public void validate_that_the_results_are_sorted_in_descending_order_of_Price() {
                String oldPrice = (passDataBetwenSteps.get("oldprice"));
                oldPrice = oldPrice.replaceAll("[^\\d.]", "");
                
                idress.clickOnPriceSortingButton1();
                idress.giveDelay(6000);
                String price2 = idress.verifyingPriceAfterSorting();
                passDataBetwenSteps.put("newPrice", price2);
                String newPrice = (passDataBetwenSteps.get("newPrice"));
                newPrice = newPrice.replaceAll("[^\\d.]", "");
                System.out.println("the new big price is " + newPrice);
                BigDecimal b = new BigDecimal(oldPrice);
                BigDecimal b1 = new BigDecimal(newPrice);
                int compareoldandnewvalue = b1.compareTo(b);
                Assert.assertTrue("the sorting is not proper", compareoldandnewvalue == 1);
               // isearch.closewebpage();
                 

        }
        
        
        @When("^User Search for item in iPriceHomePage$")
        public void user_Search_for_item_in_iPriceHomePage() {
        	isearch.enterTextinIpriceSearchBox();
        	isearch.giveDelay(3000);
        	isearch.clickonIpriceSearchButton();
        }

        @Then("^Validate that the search results returned matches the search criteria$")
        public void validate_that_the_search_results_returned_matches_the_search_criteria() {
        	List<String> alliphonedata = isearch.getDataFromSearchPage();
        	if(alliphonedata.size() > 2)
        	{
        		 scenario.write(alliphonedata.toString());
        	}
        	if(!alliphonedata.contains("Apple iPhone 14"))
        		{
        			Assert.fail("Differnt text is also same please check results");
        			
        		}
        	
        	//isearch.closewebpage();
        	
        		
        		
        	}
        
        @SuppressWarnings("deprecation")
        
//        @AfterStep
//        public void Tearup(Scenario scenario) {
//           scenario.write("Scenarionshot is taken");
//           final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
//             scenario.embed(screenshot, "image/png");
//            
//        }
        
		@After
        public void tearDown(Scenario scenario) {
            if (scenario.isFailed()) {
            	scenario.write("Scenario is failed");
             
              final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
             scenario.embed(screenshot, "image/png");
             driver.close();
             
            }
            if(driver== null)
            {
            	System.out.println("check");
            }
            else
            {
            driver.close();
            }
        }
        
        
        	
        	


}