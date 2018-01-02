package task2;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty", "html:target/cucumber"}, glue = {"task2.StepDefinitions"}, features = {"target/test-classes/features"})
public class CucumberRunner {

}
