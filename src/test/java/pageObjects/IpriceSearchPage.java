package pageObjects;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class IpriceSearchPage {

        public WebDriver driver;

        WebElement we;
      //Path of page xpaths feature file
        String xpathfilepath = "src\\main\\resources\\applicationWebpagesXpathsFiles\\iPriceSearch.properties";

        
      //Path of page xpaths feature file
        public String getValueFromPropertyFile(String filepath, String passkeyname) {

                String data = "";
                FileReader reader = null;
                try {
                        reader = new FileReader(filepath);
                } catch (FileNotFoundException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }

                Properties p = new Properties();
                try {
                        p.load(reader);
                } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }

                data = p.getProperty(passkeyname);

                return data;

        }
        
        //Instantiation Of Webdriver

        public IpriceSearchPage(WebDriver driver) {
                this.driver = driver;
                //PageFactory.initElements(driver, this);

        }

        By t1 = By.xpath(getValueFromPropertyFile(xpathfilepath, "searchvaluetextbox"));
        By t2 = By.xpath(getValueFromPropertyFile(xpathfilepath, "clicksearchbutton"));
        By t3 = By.xpath(getValueFromPropertyFile(xpathfilepath, "getsearcheddata"));

        public void enterTextinIpriceSearchBox() {
                String textToSearch = "iPhone 14";

                //		WebElement el = driver.findElement(t1);
                //        el.sendKeys(textToSearch);
                driver.findElement(t1).sendKeys(textToSearch);

        }

        public void clickonIpriceSearchButton() {

                driver.findElement(t2).click();

        }

        public List < String > getDataFromSearchPage() {
                String dataReport = "";

                List < String > allData = new ArrayList < String > ();
                List < WebElement > alliphonedata = driver.findElements(t3);
                System.out.println(alliphonedata.size());
                for (int i = 0; i < alliphonedata.size(); i++) {
                        System.out.println(alliphonedata.get(i).getText());
                        allData.add(alliphonedata.get(i).getText());
                        dataReport = dataReport + "\\n" + "    " + alliphonedata.get(i).getText();
                }

                Assert.assertTrue("please see the webpage logs", allData.size() > 1);
                return allData;

        }

        public void giveDelay(int delayinmicroseconds) {
                try {
                        Thread.sleep(delayinmicroseconds);
                } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();

                }
        }
        public void closewebpage() {
                driver.close();
        }

}