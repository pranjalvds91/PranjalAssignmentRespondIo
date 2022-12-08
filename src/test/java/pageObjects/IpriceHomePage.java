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
import org.openqa.selenium.interactions.Actions;
import io.cucumber.java.Scenario;

public class IpriceHomePage {
        public WebDriver driver;

        public Scenario scenario;

        public WebElement el;

    ////Path of page xpaths feature file
        String xpathfilepath = "src\\main\\resources\\applicationWebpagesXpathsFiles\\IpriceHomePage.properties";

        
        
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

        public IpriceHomePage(WebDriver driver, Scenario scenario, WebElement el) {
                this.driver = driver;
                this.scenario = scenario;
                this.el = el;

        }

        By t1 = By.xpath(getValueFromPropertyFile(xpathfilepath, "clicklaptop"));
        By t2 = By.xpath(getValueFromPropertyFile(xpathfilepath, "clicktoopenoption"));
        By t3 = By.xpath(getValueFromPropertyFile(xpathfilepath, "clickdell"));
        By t4 = By.xpath(getValueFromPropertyFile(xpathfilepath, "getdellinfo"));

        public void openIpriceHomePage() {
                String testenvurl = getValueFromPropertyFile("src\\main\\resources\\browserUrl.properties", "testenvurl");
                driver.get(testenvurl);

        }

        public void clickComputingPageLink() {
                Actions actions = new Actions(driver);
                actions.moveToElement(driver.findElement(t1)).click().build().perform();

        }

        public void maximizeIpriceHomePage() {
                driver.manage().window().maximize();
        }

        public void clickToOpenOption() {

                WebElement el = driver.findElement(t2);
                Actions actions = new Actions(driver);
                actions.moveToElement(el).click().build().perform();
        }
        public void clickToOptionDell() {

                WebElement el = driver.findElement(t3);
                Actions actions = new Actions(driver);
                actions.moveToElement(el).click().build().perform();

        }

        public List < String > getDataFromDellPage() {
                String dataReport = "";

                List < String > allData = new ArrayList < String > ();
                List < WebElement > allaptopnames = driver.findElements(t4);
                System.out.println(allaptopnames.size());
                for (int i = 0; i < allaptopnames.size(); i++) {
                        System.out.println(allaptopnames.get(i).getText());
                        allData.add(allaptopnames.get(i).getText());
                        dataReport = dataReport + "\\n" + "    " + allaptopnames.get(i).getText();
                        // actions.writeDataToReport(allData.get(i));
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