package AutomatedWebTesting.StepDefinitions;

import AutomatedWebTesting.PageObjects.ContactSupervisorForm;
import AutomatedWebTesting.PageObjects.LoginForm;
import AutomatedWebTesting.PageObjects.MessagingForm;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import servlets.StaticVariables;

import static org.junit.Assert.assertEquals;

public class WebAppStepDefs {

    // Properties
    WebDriver browser;
    ContactSupervisorForm csForm;
    LoginForm lForm;
    MessagingForm mForm;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        browser = new ChromeDriver();
    }

    @After
    public void teardown() {
        browser.quit();
        StaticVariables.Erase();
    }

    @Given("^I am an agent trying to log in$")
    public void i_am_an_agent_trying_to_log_in() throws Throwable {
        browser.get("localhost:8080/");
    }

    @When("^I obtain a key from the supervisor using a valid id \"([^\"]*)\" \"([^\"]*)\"$")
    public void i_obtain_a_key_from_the_supervisor_using_a_valid_id(String agentID, String agentName) throws Exception {
        csForm = new ContactSupervisorForm(browser);
        csForm.goTo();
        csForm.populate(agentID, agentName);
        csForm.submit();
    }

    @Then("^the supervisor should give me a valid key$")
    public void the_supervisor_should_give_me_a_valid_key() throws Exception {
        assertEquals("Login Key Request", csForm.getPageTitle());
    }

    @When("^I log in using that key$")
    public void i_log_in_using_that_key() throws Exception {
        lForm = new LoginForm(browser);
        lForm.goTo();
        lForm.submit();
    }

    @Then("^I should be allowed to log in \"([^\"]*)\"$")
    public void i_should_be_allowed_to_log_in(String expectedTitle) throws Exception {
        assertEquals(expectedTitle, lForm.getPageTitle());
    }

    @When("^I wait for (\\d+) seconds$")
    public void i_wait_for_seconds(String time) throws Exception {
        Thread.sleep(Integer.parseInt(time) * 1000);
    }

    @Then("^I should not be allowed to log in \"([^\"]*)\"$")
    public void i_should_not_be_allowed_to_log_in(String error) throws Exception {
        assertEquals(error, csForm.find("error").getText());
    }

    @Given("^I am a logged in agent$")
    public void i_am_a_logged_in_agent() throws Exception {

        // Login the agent
        browser.get("localhost:8080/");
        csForm = new ContactSupervisorForm(browser);
        lForm = new LoginForm(browser);
        csForm.getKey("001", "Jane Doe");
        lForm.login();
    }

    @When("^I attempt to send (\\d+) messages$")
    public void i_attempt_to_send_messages(int amount) throws Exception {

        mForm = new MessagingForm(browser);
        for(int i = 0; i < amount; i++){
            mForm.sendMessage(Integer.toString(i), "Testing 1... 2... 3...");
        }
    }

    @Then("^the messages should be successfully sent$")
    public void the_messages_should_be_successfully_sent() throws Exception {
        assertEquals("Message Sent", mForm.find("error").getText());
    }

    @When("^I try to send another message$")
    public void i_try_to_send_another_message() throws Exception {
        mForm.sendMessage("26", "This should be an error!");
    }

    @Then("^the system will inform me that I have exceeded my quota \"([^\"]*)\"$")
    public void the_system_will_inform_me_that_I_have_exceeded_my_quota(String title) throws Exception {
        assertEquals(title, mForm.getPageTitle());
    }

    @Then("^I will be logged out \"([^\"]*)\"$")
    public void i_will_be_logged_out(String title) throws Exception {
        mForm.find("logoutButton").click();
        assertEquals(title, mForm.getPageTitle());
    }

    @When("^I click on log out$")
    public void i_click_on_log_out() throws Exception {
        mForm = new MessagingForm(browser);
        mForm.find("logoutButton").click();
    }

    @Then("^I should be logged out \"([^\"]*)\"$")
    public void i_should_be_logged_out(String pageTitle) throws Exception {
        assertEquals(pageTitle, mForm.getPageTitle());
    }

    @When("^I attempt to send the message (.*) to another agent$")
    public void i_attempt_to_send_the_message_to_another_agent(String message) throws Exception {

        // Login the agent
        browser.get("localhost:8080/");
        csForm = new ContactSupervisorForm(browser);
        lForm = new LoginForm(browser);
        csForm.getKey("001", "Jane Doe");
        lForm.login();

        // Send a message
        mForm = new MessagingForm(browser);
        mForm.sendMessage("002", message);

        // Logout agent
        mForm.find("logoutButton").click();
    }

    @Then("^the other agent should receive the message (.*)$")
    public void the_other_agent_should_receive_the_message(String message) throws Exception {

        // Login the agent
        browser.get("localhost:8080/");
        csForm = new ContactSupervisorForm(browser);
        lForm = new LoginForm(browser);
        csForm.getKey("002", "Twistee Doe");
        lForm.login();

        // Get next message
        mForm.nextMessage();
        assertEquals(message, mForm.find("newMessage").getText());
    }
}