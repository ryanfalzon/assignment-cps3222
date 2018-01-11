package ModelTesting;

import UnitTesting.Agent;
import nz.ac.waikato.modeljunit.*;
import nz.ac.waikato.modeljunit.coverage.ActionCoverage;
import nz.ac.waikato.modeljunit.coverage.StateCoverage;
import nz.ac.waikato.modeljunit.coverage.TransitionPairCoverage;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import servlets.StaticVariables;

import java.util.Random;

import static org.junit.Assert.assertEquals;

public class SystemModel implements FsmModel {

    // Properties
    private WebDriver browser;
    private States modelState;
    private boolean homePage, contactSupervisorPage, loginKeyMessagePage, loginKeyErrorPage, loginPage, messagingPage, automaticLogoutPage;
    private int i = 0;  // Used for AutomaticLogout Action

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

        // Perform the action
        if(getState().equals(States.Home_Page)){
            browser.findElement(By.name("contactButton")).click();
            homePage = false;
        }
        else if(getState().equals(States.Login_Key_Error_Page)){
            browser.findElement(By.name("backButton")).click();
            loginKeyErrorPage = false;
        }
        contactSupervisorPage = true;
        modelState = States.Contact_Supervisor_Page;

        // Assert
        assertEquals("The model's Contact_Supervisor_Page state does not match the SUT's state.", contactSupervisorPage, browser.getTitle().equals("Contact Supervisor"));
    }

    // ValidKeyDetails Guard
    public boolean validKeyDetailsGuard(){
        return getState().equals(States.Contact_Supervisor_Page);
    }

    // ValidKeyDetails Action
    public @Action void validKeyDetails(){

        // Perform the action
        browser.findElement(By.name("id")).sendKeys("001");
        browser.findElement(By.name("name")).sendKeys("Ryan Falzon");
        browser.findElement(By.name("getKeyButton")).click();

        loginKeyMessagePage = true;
        contactSupervisorPage = false;
        modelState = States.Login_Key_Message_Page;

        // Assert
        assertEquals("The model's Login_Key_Message_Page state does not match the SUT's state.", loginKeyMessagePage, browser.getTitle().equals("Login Key Request"));
    }

    // InvalidKeyDetails Guard
    public boolean invalidKeyDetailsGuard(){
        return getState().equals(States.Contact_Supervisor_Page);
    }

    // InvalidKeyDetails Action
    public @Action void invalidKeyDetails(){

        // Perform the action
        browser.findElement(By.name("id")).sendKeys("spy");
        browser.findElement(By.name("name")).sendKeys("Ryan Falzon");
        browser.findElement(By.name("getKeyButton")).click();

        loginKeyErrorPage = true;
        contactSupervisorPage = false;
        modelState = States.Login_Key_Error_Page;

        // Assert
        assertEquals("The model's Login_Key_Error_Page state does not match the SUT's state.", loginKeyErrorPage, browser.getTitle().equals("Request Not Approved"));
    }

    // RedirectionHomePage Guard
    public boolean redirectionHomePageGuard(){
        return (getState().equals(States.Contact_Supervisor_Page) || (getState().equals(States.Login_Page)));
    }

    // RedirectionHomePage Action
    public @Action void redirectionHomePage(){

        // Perform the action
        browser.findElement(By.name("backButton")).click();
        if(modelState.equals(States.Contact_Supervisor_Page)){
            contactSupervisorPage = false;
        }
        else if(modelState.equals(States.Login_Page)){
            loginPage = false;
        }
        homePage = true;
        modelState = States.Home_Page;

        // Assert
        assertEquals("The model's Home_Page state does not match the SUT's state.", homePage, browser.getTitle().equals("Home Page"));
    }

    // Logout Guard
    public boolean logoutGuard(){
        return (getState().equals(States.Automatic_Logout_Page) || getState().equals(States.Messaging_Page));
    }

    // Logout Action
    public @Action void logout(){

        // Perform the action
        browser.findElement(By.name("logoutButton")).click();
        if(modelState.equals(States.Messaging_Page)){
            messagingPage = false;
        }
        else if(modelState.equals(States.Automatic_Logout_Page)){
            automaticLogoutPage = false;
        }
        homePage = true;
        modelState = States.Home_Page;

        // Assert
        assertEquals("The model's Home_Page state does not match the SUT's state.", homePage, browser.getTitle().equals("Home Page"));
    }

    // Login Guard
    public boolean loginGuard(){
        return getState().equals(States.Login_Key_Message_Page);
    }

    // Login Action
    public @Action void login(){

        // Perform the action
        browser.findElement(By.name("loginButton")).click();
        loginPage = true;
        loginKeyMessagePage = false;
        modelState = States.Login_Page;

        // Assert
        assertEquals("The model's Login_Page state does not match the SUT's state.", loginPage, browser.getTitle().equals("Login Page"));
    }

    // ValidAgentDetails Guard
    public boolean validAgentDetailsGuard(){
        return getState().equals(States.Login_Page);
    }

    // ValidAgentDetails Action
    public @Action void validAgentDetails(){

        // Perform action
        browser.findElement(By.name("loginbutton")).click();
        loginPage = false;
        messagingPage = true;
        modelState = States.Messaging_Page;

        // Assert
        assertEquals("The model's Messaging_Page state does not match the SUT's state.", messagingPage, browser.getTitle().equals("Messaging Page"));
    }

    // InvalidAgentDetails Guard
    public boolean invalidAgentDetailsGuard(){
        return getState().equals(States.Login_Page);
    }

    // InvalidAgentDetails Action
    public @Action void invalidAgentDetails(){

        // Perform action
        browser.findElement(By.name("loginkey")).sendKeys("invalidloginkey");
        browser.findElement(By.name("loginbutton")).click();

        // Assert
        assertEquals("The model's Login_Page state does not match the SUT's state.", loginPage, browser.getTitle().equals("Login Page"));
        assertEquals("Error message not displayed", true, (!browser.findElement(By.name("error")).getText().equals("")));
    }

    // ValidMessage Guard
    public boolean validMessageGuard(){
        return getState().equals(States.Messaging_Page);
    }

    // ValidMessage Action
    public @Action void validMessage(){

        // Perform the action
        browser.findElement(By.name("targetagent")).sendKeys("002");
        browser.findElement(By.name("message")).sendKeys("Hello how are you?");
        browser.findElement(By.name("submitmessage")).click();

        if(browser.getTitle().equals("Automatic Logout")){
            browser.findElement(By.name("logoutButton")).click();
            browser.findElement(By.name("contactButton")).click();
            browser.findElement(By.name("loginButton")).click();
            browser.findElement(By.name("loginbutton")).click();
        }

        // Assert
        assertEquals("The model's Messaging_Page state does not match the SUT's state.", messagingPage, browser.getTitle().equals("Messaging Page"));
    }

    // InvalidMessage Guard
    public boolean invalidMessageGuard(){
        return getState().equals(States.Messaging_Page);
    }

    // InvalidMessage Action
    public @Action void invalidMessage(){

        // Perform the action
        browser.findElement(By.name("targetagent")).sendKeys("002");
        browser.findElement(By.name("message")).sendKeys("abcdefghijabcdefghijabcdefghijabcdefghijabcdefghijabcdefghijabcdefghijabcdefghijabcdefghijabcdefghijabcdefghijabcdefghijabcdefghijabcdefghijabcdefghij");
        browser.findElement(By.name("submitmessage")).click();

        // Assert
        assertEquals("The model's Messaging_Page state does not match the SUT's state.", messagingPage, browser.getTitle().equals("Messaging Page"));
        assertEquals("Message was sent successfully.", false, browser.findElement(By.name("error")).getText().equals("Message Sent"));
    }

    // NextMessage Guard
    public boolean nextMessageGuard(){
        return getState().equals(States.Messaging_Page);
    }

    // NextMessage Action
    public @Action void nextMessage(){

        // Perform the action
        browser.findElement(By.name("next")).click();

        // Assert
        assertEquals("The model's Messaging_Page state does not match the SUT's state.", messagingPage, browser.getTitle().equals("Messaging Page"));
        assertEquals("Next message was not consumed.", true, (!browser.findElement(By.name("newMessage")).getText().equals("")));
    }

    // HasMessage Guard
    public boolean hasMessageGuard(){
        return getState().equals(States.Messaging_Page);
    }

    // HasMessage Action
    public @Action void hasMessage(){

        // Perform the action
        browser.findElement(By.name("count")).click();

        // Assert
        assertEquals("The model's Messaging_Page state does not match the SUT's state.", messagingPage, browser.getTitle().equals("Messaging Page"));
        assertEquals("Next message was not consumed.", true, (!browser.findElement(By.name("checkCount")).getText().equals("")));
    }

    // AutomaticLogout Guard
    public boolean automaticLogoutGuard(){
        return getState().equals(States.Messaging_Page);
    }

    // AutomaticLogout Action
    public @Action void automaticLogout(){

        // Perform the action
        StaticVariables.agents.clear();
        while(browser.getTitle().equals("Messaging Page")){

            // Send the message
            browser.findElement(By.name("targetagent")).sendKeys(Integer.toString(i));
            browser.findElement(By.name("message")).sendKeys("Testing 1... 2... 3...");
            browser.findElement(By.name("submitmessage")).click();
            i++;
        }
        messagingPage = false;
        automaticLogoutPage = true;
        modelState = States.Automatic_Logout_Page;

        // Assert
        assertEquals("The model's Automatic_Logout_Page state does not match the SUT's state.", automaticLogoutPage, browser.getTitle().equals("Automatic Logout"));
    }

    @Test
    public void MessagingSystemModelRunner() {
        final Tester tester = new GreedyTester(new SystemModel());
        tester.setRandom(new Random());
        tester.buildGraph();
        tester.addListener(new StopOnFailureListener());
        tester.addListener("verbose");
        tester.addCoverageMetric(new TransitionPairCoverage());
        tester.addCoverageMetric(new StateCoverage());
        tester.addCoverageMetric(new ActionCoverage());
        tester.generate(3000);
        tester.printCoverage();
    }
}