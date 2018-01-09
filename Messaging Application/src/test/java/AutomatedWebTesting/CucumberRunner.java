package AutomatedWebTesting;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty", "html:target/cucumber"}, glue = {"AutomatedWebTesting.StepDefinitions"}, features = {"target/test-classes/features"})
public class CucumberRunner {

}
