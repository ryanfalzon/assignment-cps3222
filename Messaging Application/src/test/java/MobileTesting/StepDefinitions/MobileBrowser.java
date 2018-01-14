package MobileTesting.StepDefinitions;

import MobileTesting.MobilePageObjects.MobileContactSupervisorForm;
import MobileTesting.MobilePageObjects.MobileLoginForm;
import MobileTesting.MobilePageObjects.MobileMessagingForm;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.android.AndroidDriver;
import servlets.StaticVariables;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.assertEquals;

public class MobileBrowser {

    // Properties
    private AndroidDriver device;
    private MobileContactSupervisorForm mobileContactSupervisorForm;
    private MobileLoginForm mobileLoginForm;
    private MobileMessagingForm mobileMessagingForm;

    @Before
    public void setup() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "Nexus_6_API_22");
        capabilities.setCapability("platformVersion", "5.1");
        capabilities.setCapability("browserName", "Browser");
        capabilities.setCapability("deviceOrientation", "portrait");
        capabilities.setCapability("appiumVersion", "1.7.2");
        capabilities.setCapability("newCommandTimeout", 60 * 5);

        System.setProperty("java.net.preferIPv4Stack" , "true");
        device = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
    }

    @After
    public void teardown(){
        device.quit();
        StaticVariables.Erase();
    }

    @Given("^I am an agent trying to log in$")
    public void i_am_an_agent_trying_to_log_in() throws Throwable {
        device.navigate().to("http://192.168.0.102:8080");
    }

    @When("^I obtain a key from the supervisor using a valid id \"([^\"]*)\" \"([^\"]*)\"$")
    public void i_obtain_a_key_from_the_supervisor_using_a_valid_id(String agentID, String agentName) throws Exception {
        mobileContactSupervisorForm = new MobileContactSupervisorForm(device);
        mobileContactSupervisorForm.goTo();
        mobileContactSupervisorForm.populate(agentID, agentName);
        mobileContactSupervisorForm.submit();
    }

    @Then("^the supervisor should give me a valid key$")
    public void the_supervisor_should_give_me_a_valid_key() throws Exception {
        assertEquals("Login Key Request", mobileContactSupervisorForm.getPageTitle());
    }

    @When("^I log in using that key$")
    public void i_log_in_using_that_key() throws Exception {
        mobileLoginForm = new MobileLoginForm(device);
        mobileLoginForm.goTo();
        mobileLoginForm.submit();
    }

    @Then("^I should be allowed to log in \"([^\"]*)\"$")
    public void i_should_be_allowed_to_log_in(String expectedTitle) throws Exception {
        assertEquals(expectedTitle, mobileLoginForm.getPageTitle());
    }

    @When("^I wait for (\\d+) seconds$")
    public void i_wait_for_seconds(String time) throws Exception {
        Thread.sleep(1000 * 65);
    }

    @Then("^I should not be allowed to log in \"([^\"]*)\"$")
    public void i_should_not_be_allowed_to_log_in(String error) throws Exception {
        assertEquals(error, mobileContactSupervisorForm.find("error").getText());
    }

    @Given("^I am a logged in agent$")
    public void i_am_a_logged_in_agent() throws Exception {

        // Login the agent
        device.navigate().to("http://192.168.0.102:8080");
        mobileContactSupervisorForm = new MobileContactSupervisorForm(device);
        mobileLoginForm = new MobileLoginForm(device);
        mobileContactSupervisorForm.getKey("001", "Jane Doe");
        mobileLoginForm.goTo();
        mobileLoginForm.submit();
    }

    @When("^I attempt to send (\\d+) messages$")
    public void i_attempt_to_send_messages(int amount) throws Exception {

        mobileMessagingForm = new MobileMessagingForm(device);
        for(int i = 0; i < amount; i++){
            mobileMessagingForm.sendMessage(Integer.toString(i), "Testing 1... 2... 3...");
        }
    }

    @Then("^the messages should be successfully sent$")
    public void the_messages_should_be_successfully_sent() throws Exception {
        assertEquals("Message Sent", mobileMessagingForm.find("error").getText());
    }

    @When("^I try to send another message$")
    public void i_try_to_send_another_message() throws Exception {
        mobileMessagingForm.sendMessage("26", "This should be an error!");
    }

    @Then("^the system will inform me that I have exceeded my quota \"([^\"]*)\"$")
    public void the_system_will_inform_me_that_I_have_exceeded_my_quota(String title) throws Exception {
        assertEquals(title, mobileMessagingForm.getPageTitle());
    }

    @Then("^I will be logged out \"([^\"]*)\"$")
    public void i_will_be_logged_out(String title) throws Exception {
        mobileMessagingForm.find("logoutButton").click();
        assertEquals(title, mobileMessagingForm.getPageTitle());
    }

    @When("^I click on log out$")
    public void i_click_on_log_out() throws Exception {
        mobileMessagingForm = new MobileMessagingForm(device);
        mobileMessagingForm.find("logoutButton").click();
    }

    @Then("^I should be logged out \"([^\"]*)\"$")
    public void i_should_be_logged_out(String pageTitle) throws Exception {
        assertEquals(pageTitle, mobileMessagingForm.getPageTitle());
    }

    @When("^I attempt to send the message (.*) to another agent$")
    public void i_attempt_to_send_the_message_to_another_agent(String message) throws Exception {

        // Login the agent
        device.navigate().to("http://192.168.0.102:8080");
        mobileContactSupervisorForm = new MobileContactSupervisorForm(device);
        mobileLoginForm = new MobileLoginForm(device);
        mobileContactSupervisorForm.getKey("001", "Jane Doe");
        mobileLoginForm.login();

        // Send a message
        mobileMessagingForm = new MobileMessagingForm(device);
        mobileMessagingForm.sendMessage("002", message);

        // Logout agent
        mobileMessagingForm.find("logoutButton").click();
    }

    @Then("^the other agent should receive the message (.*)$")
    public void the_other_agent_should_receive_the_message(String message) throws Exception {

        // Login the agent
        device.navigate().to("http://192.168.0.102:8080");
        mobileContactSupervisorForm = new MobileContactSupervisorForm(device);
        mobileLoginForm = new MobileLoginForm(device);
        mobileContactSupervisorForm.getKey("002", "Twistee Doe");
        mobileLoginForm.login();

        // Get next message
        mobileMessagingForm.nextMessage();
        assertEquals(message, mobileMessagingForm.find("newMessage").getText());
    }
}
