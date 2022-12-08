import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src\\test\\resources\\features"
		,glue={"stepDefinition"},
		       strict = true,
				
				plugin = { "pretty",
                        "json:target/cucumber-report/cucumber.json",
                        "html:target/cucumber-report/cucumber.html",
                        "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
                        
		},
						
				          monochrome = true
				          
		)

///SeleniumAutomation/src/test/resources/features/SeleniumIpriceScenarios.feature

public class CucumberRunner {
	

}
