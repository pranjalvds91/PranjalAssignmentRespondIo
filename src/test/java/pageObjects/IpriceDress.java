package pageObjects;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class IpriceDress {
        public WebDriver driver;
        
        //Instantiation Of Webdriver

        public IpriceDress(WebDriver driver) {
                this.driver = driver;

        }
        //Path of page xpaths feature file
        String xpathfilepath = "src\\main\\resources\\applicationWebpagesXpathsFiles\\IPriceDress.properties";

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
         
        public void clickOnDressLink() {
                String xpathval = getValueFromPropertyFile(xpathfilepath, "clickdress");
                WebElement el = driver.findElement(By.xpath(xpathval));
                Actions actions = new Actions(driver);
                actions.moveToElement(el).click().build().perform();
        }

        public void clickOnPriceSortingButton() {
                String xpathval = getValueFromPropertyFile(xpathfilepath, "clickprice");
                WebElement el = driver.findElement(By.xpath(xpathval));
                Actions actions = new Actions(driver);
                actions.moveToElement(el).click().build().perform();
        }

        public void clickOnPriceSortingButton1() {
                String xpathvalue = getValueFromPropertyFile(xpathfilepath, "clickprice");
                WebElement el = driver.findElement(By.xpath(xpathvalue));
                el.click();
        }

        public String verifyingPriceAfterSorting()

        {
                String textfromElement = "";

                String xpathvalue = getValueFromPropertyFile(xpathfilepath, "checkpricefirst");
                WebElement el = driver.findElement(By.xpath(xpathvalue));
                textfromElement = el.getText();
                return textfromElement;

        }

        public void giveDelay(int delayinmicroseconds) {
                try {
                        Thread.sleep(delayinmicroseconds);
                } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();

                }
        }

}