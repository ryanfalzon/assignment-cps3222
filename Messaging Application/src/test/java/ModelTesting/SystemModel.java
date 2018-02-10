package ModelTesting;

import AutomatedWebTesting.PageObjects.ContactSupervisorForm;
import AutomatedWebTesting.PageObjects.Form;
import AutomatedWebTesting.PageObjects.LoginForm;
import AutomatedWebTesting.PageObjects.MessagingForm;
import nz.ac.waikato.modeljunit.*;
import nz.ac.waikato.modeljunit.coverage.ActionCoverage;
import nz.ac.waikato.modeljunit.coverage.StateCoverage;
import nz.ac.waikato.modeljunit.coverage.TransitionPairCoverage;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import servlets.ContactSupervisor;
import servlets.StaticVariables;

import javax.xml.datatype.Duration;
import java.time.Instant;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class SystemModel implements FsmModel {

    // Properties
    private WebDriver browser;
    private States modelState;
    private boolean homePage, contactSupervisorPage, loginKeyMessagePage, loginKeyErrorPage, loginPage, messagingPage, automaticLogoutPage;
    private int i = 0;  // Used for AutomaticLogout Action
    Random randomGenerator = new Random();      // Random number generator to choose which agent will login

    // Constructor
    public SystemModel(){
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        browser = new ChromeDriver();
    }

    // Method that will return a state
    public States getState() {
        return modelState;
    }

    // Method to reset the model
    public void reset(boolean b) {

        // Reset boolean values
        homePage = true;
        contactSupervisorPage = false;
        loginKeyMessagePage = false;
        loginKeyErrorPage = false;
        loginPage = false;
        messagingPage = false;
        automaticLogoutPage = false;

        // Reset the state
        modelState = States.Home_Page;

        // Check boolean value passed
        if(b){
            StaticVariables.Erase();
            browser.get("localhost:8080");
        }
    }

    // ContactingSupervisor Guard
    public boolean contactSupervisorGuard(){
        return (getState().equals(States.Home_Page) || getState().equals(States.Login_Key_Error_Page));
    }

    // ContactingSupervisor Action
    public @Action void contactSupervisor(){

        Form form = new Form(browser);

        // Perform the action
        if(getState().equals(States.Home_Page)){
            form.find("contactButton").click();
            homePage = false;
        }
        else if(getState().equals(States.Login_Key_Error_Page)){
            form.find("backButton").click();
            loginKeyErrorPage = false;
        }
        contactSupervisorPage = true;
        modelState = States.Contact_Supervisor_Page;

        // Assert
        assertEquals("The model's Contact_Supervisor_Page state does not match the SUT's state.", contactSupervisorPage, form.getPageTitle().equals("Contact Supervisor"));
    }

    // ValidKeyDetails Guard
    public boolean validKeyDetailsGuard(){
        return getState().equals(States.Contact_Supervisor_Page);
    }

    // ValidKeyDetails Action
    public @Action void validKeyDetails(){

        ContactSupervisorForm csForm = new ContactSupervisorForm(browser);

        // Generate a random number to see which agent will login
        int randomNumber = randomGenerator.nextInt(2);

        // Perform the action
        if(randomNumber == 0){
            csForm.populate("001", "Ryan Falzon");
        }
        else if(randomNumber == 1){
            csForm.populate("002", "Kristi Muscat");
        }
        csForm.submit();

        loginKeyMessagePage = true;
        contactSupervisorPage = false;
        modelState = States.Login_Key_Message_Page;

        // Assert
        assertEquals("The model's Login_Key_Message_Page state does not match the SUT's state.", loginKeyMessagePage, csForm.getPageTitle().equals("Login Key Request"));
    }

    // InvalidKeyDetails Guard
    public boolean invalidKeyDetailsGuard(){
        return getState().equals(States.Contact_Supervisor_Page);
    }

    // InvalidKeyDetails Action
    public @Action void invalidKeyDetails(){

        // Perform the action
        ContactSupervisorForm csForm = new ContactSupervisorForm(browser);
        csForm.populate("spy", "James Bond");
        csForm.submit();

        loginKeyErrorPage = true;
        contactSupervisorPage = false;
        modelState = States.Login_Key_Error_Page;

        // Assert
        assertEquals("The model's Login_Key_Error_Page state does not match the SUT's state.", loginKeyErrorPage, csForm.getPageTitle().equals("Request Not Approved"));
    }

    // RedirectionHomePage Guard
    public boolean redirectionHomePageGuard(){
        return (getState().equals(States.Contact_Supervisor_Page) || (getState().equals(States.Login_Page)));
    }

    // RedirectionHomePage Action
    public @Action void redirectionHomePage(){

        // Perform the action
        Form form = new Form(browser);
        form.find("backButton").click();
        if(modelState.equals(States.Contact_Supervisor_Page)){
            contactSupervisorPage = false;
        }
        else if(modelState.equals(States.Login_Page)){
            loginPage = false;
        }
        homePage = true;
        modelState = States.Home_Page;

        // Assert
        assertEquals("The model's Home_Page state does not match the SUT's state.", homePage, form.getPageTitle().equals("Home Page"));
    }

    // Logout Guard
    public boolean logoutGuard(){
        return (getState().equals(States.Automatic_Logout_Page) || getState().equals(States.Messaging_Page));
    }

    // Logout Action
    public @Action void logout(){

        // Perform the action
        Form form = new Form(browser);
        form.find("logoutButton").click();
        if(modelState.equals(States.Messaging_Page)){
            messagingPage = false;
        }
        else if(modelState.equals(States.Automatic_Logout_Page)){
            automaticLogoutPage = false;
        }
        homePage = true;
        modelState = States.Home_Page;

        // Assert
        assertEquals("The model's Home_Page state does not match the SUT's state.", homePage, form.getPageTitle().equals("Home Page"));
    }

    // Login Guard
    public boolean loginGuard(){
        return getState().equals(States.Login_Key_Message_Page);
    }

    // Login Action
    public @Action void login(){

        // Perform the action
        Form form = new Form(browser);
        form.find("loginButton").click();
        loginPage = true;
        loginKeyMessagePage = false;
        modelState = States.Login_Page;

        // Assert
        assertEquals("The model's Login_Page state does not match the SUT's state.", loginPage, form.getPageTitle().equals("Login Page"));
    }

    // ValidAgentDetails Guard
    public boolean validAgentDetailsGuard(){
        return getState().equals(States.Login_Page);
    }

    // ValidAgentDetails Action
    public @Action void validAgentDetails(){

        // Perform action
        LoginForm lForm = new LoginForm(browser);
        lForm.submit();
        loginPage = false;
        messagingPage = true;
        modelState = States.Messaging_Page;

        // Assert
        assertEquals("The model's Messaging_Page state does not match the SUT's state.", messagingPage, lForm.getPageTitle().equals("Messaging Page"));
    }

    // InvalidAgentDetails Guard
    public boolean invalidAgentDetailsGuard(){
        return getState().equals(States.Login_Page);
    }

    // InvalidAgentDetails Action
    public @Action void invalidAgentDetails(){

        // Perform action
        LoginForm lForm = new LoginForm(browser);
        lForm.populate("001", "invalidloginkey");
        lForm.submit();

        // Assert
        assertEquals("The model's Login_Page state does not match the SUT's state.", loginPage, lForm.getPageTitle().equals("Login Page"));
        assertEquals("Error message not displayed", true, (!lForm.find("error").getText().equals("")));
    }

    // ValidMessage Guard
    public boolean validMessageGuard(){
        return getState().equals(States.Messaging_Page);
    }

    // ValidMessage Action
    public @Action void validMessage(){

        // Perform the action
        MessagingForm mForm = new MessagingForm(browser);
        mForm.sendMessage("002", "Hello, how are you?");

        if(browser.getTitle().equals("Automatic Logout")){
            mForm.find("logoutButton").click();
            mForm.find("contactButton").click();
            mForm.find("loginButton").click();
            mForm.find("loginbutton").click();
        }

        // Assert
        assertEquals("The model's Messaging_Page state does not match the SUT's state.", messagingPage, mForm.getPageTitle().equals("Messaging Page"));
    }

    // InvalidMessage Guard
    public boolean invalidMessageGuard(){
        return getState().equals(States.Messaging_Page);
    }

    // InvalidMessage Action
    public @Action void invalidMessage(){

        // Perform the action
        MessagingForm mForm = new MessagingForm(browser);
        mForm.sendMessage("002", "abcdefghijabcdefghijabcdefghijabcdefghijabcdefghijabcdefghijabcdefghijabcdefghijabcdefghijabcdefghijabcdefghijabcdefghijabcdefghijabcdefghijabcdefghij");

        // Assert
        assertEquals("The model's Messaging_Page state does not match the SUT's state.", messagingPage, mForm.getPageTitle().equals("Messaging Page"));
        assertEquals("Message was sent successfully.", false, mForm.find("error").getText().equals("Message Sent"));
    }

    // NextMessage Guard
    public boolean nextMessageGuard(){
        return getState().equals(States.Messaging_Page);
    }

    // NextMessage Action
    public @Action void nextMessage(){

        // Perform the action
        MessagingForm mForm = new MessagingForm(browser);
        mForm.nextMessage();

        // Assert
        assertEquals("The model's Messaging_Page state does not match the SUT's state.", messagingPage, mForm.getPageTitle().equals("Messaging Page"));
        assertEquals("Next message was not consumed.", true, (!mForm.find("newMessage").getText().equals("")));
    }

    // HasMessage Guard
    public boolean hasMessageGuard(){
        return getState().equals(States.Messaging_Page);
    }

    // HasMessage Action
    public @Action void hasMessage(){

        // Perform the action
        MessagingForm mForm = new MessagingForm(browser);
        mForm.hasMessaage();

        // Assert
        assertEquals("The model's Messaging_Page state does not match the SUT's state.", messagingPage, mForm.getPageTitle().equals("Messaging Page"));
        assertEquals("Next message was not consumed.", true, (!mForm.find("checkCount").getText().equals("")));
    }

    // AutomaticLogout Guard
    public boolean automaticLogoutGuard(){
        return getState().equals(States.Messaging_Page);
    }

    // AutomaticLogout Action
    public @Action void automaticLogout(){

        // Perform the action
        StaticVariables.agents.clear();
        MessagingForm mForm = new MessagingForm(browser);
        while(browser.getTitle().equals("Messaging Page")){

            // Send the message
            mForm.sendMessage(Integer.toString(i), "Testing 1... 2... 3...");
            i++;
        }
        messagingPage = false;
        automaticLogoutPage = true;
        modelState = States.Automatic_Logout_Page;

        // Assert
        assertEquals("The model's Automatic_Logout_Page state does not match the SUT's state.", automaticLogoutPage, mForm.getPageTitle().equals("Automatic Logout"));
    }

    @Test
    public void MessagingSystemModelRunner() {

        // Some metrics for duration
        final java.time.Duration TEST_DURATION = java.time.Duration.ofMinutes(15);
        final Instant startTime = Instant.now();
        final Instant finishTime = startTime.plus(TEST_DURATION);

        final Tester tester = new GreedyTester(new SystemModel());
        tester.setRandom(new Random());
        tester.buildGraph();
        tester.addListener(new StopOnFailureListener());
        tester.addListener("verbose");
        tester.addCoverageMetric(new TransitionPairCoverage());
        tester.addCoverageMetric(new StateCoverage());
        tester.addCoverageMetric(new ActionCoverage());
        while (Instant.now().isBefore(finishTime)) {
            tester.generate();
        }
        tester.printCoverage();
    }
}