package ModelTesting;

import nz.ac.waikato.modeljunit.*;
import nz.ac.waikato.modeljunit.coverage.ActionCoverage;
import nz.ac.waikato.modeljunit.coverage.StateCoverage;
import nz.ac.waikato.modeljunit.coverage.TransitionPairCoverage;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Random;

import static org.junit.Assert.assertEquals;

public class SystemModel implements FsmModel {

    // Properties
    private WebDriver browser;
    private States modelState;
    private boolean homePage, contactSupervisorPage, loginKeyMessagePage, loginPage, messagingPage, automaticLogoutPage;

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
        loginPage = false;
        messagingPage = false;
        automaticLogoutPage = false;

        // Reset the state
        modelState = States.Home_Page;

        // Check boolean value passed
        if(b){
            browser.get("localhost:8080");
        }
    }

    // ContactingSupervisor Guard
    public boolean contactSupervisorGuard(){
        return getState().equals(States.Home_Page);
    }

    // ContactingSupervisor Action
    public @Action void contactSupervisor(){

        // Perform the action
        browser.findElement(By.name("contactButton")).click();
        contactSupervisorPage = true;
        homePage = false;
        modelState = States.Contact_Supervisor_Page;

        // Assert
        assertEquals("The model's Contact_Supervisor_Page state does not match the SUT's state.", contactSupervisorPage, browser.getTitle().equals("Contact Supervisor"));
    }

    // ValidDetails Guard
    public boolean validKeyDetailsGuard(){
        return getState().equals(States.Contact_Supervisor_Page);
    }

    // ValidDetails Action
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

    // RedirectionHomePage Guard
    public boolean redirectionHomePageGuard(){
        return (getState().equals(States.Login_Key_Message_Page) || (getState().equals(States.Contact_Supervisor_Page))
                || (getState().equals(States.Login_Page)));
    }

    // RedirectionHomePage Action
    public @Action void redirectionHomePage(){

        // Perform the action
        browser.findElement(By.name("backButton")).click();
        if(modelState.equals(States.Login_Key_Message_Page)){
            loginKeyMessagePage = false;
        }
        else if(modelState.equals(States.Contact_Supervisor_Page)){
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

        // Assert
        assertEquals("The model's Messaging_Page state does not match the SUT's state.", messagingPage, browser.getTitle().equals("Messaging Page"));
        assertEquals("Message was not sent successful.", "Message Sent", browser.findElement(By.name("error")).getText());

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
        tester.generate(250);
        tester.printCoverage();
    }
}
