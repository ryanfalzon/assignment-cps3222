package task3;

import backend.Agent;
import backend.MessagingSystem;
import nz.ac.waikato.modeljunit.*;
import nz.ac.waikato.modeljunit.coverage.ActionCoverage;
import nz.ac.waikato.modeljunit.coverage.StateCoverage;
import nz.ac.waikato.modeljunit.coverage.TransitionPairCoverage;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import javax.swing.plaf.nimbus.State;

import java.util.Random;

import static org.junit.Assert.assertEquals;

public class ModelTest implements FsmModel {

    // Properties
    private WebDriver browser;
    private States modelState;
    private boolean proceeding_to_homepage, proceeding_to_loginpage, proceeding_to_messagingpage, contacting_supervisor, invalid_agent_details, valid_agent_details, invalid_login_details,
                    valid_login_details, submitting_valid_message, submitting_invalid_message, getting_next_message, getting_message_count, automatically_loggingout, logging_out;

    // Constructor
    public ModelTest(){
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        browser = new ChromeDriver();
    }

    // Method that will return a state
    public States getState() {

        // Check in which page the user is in
        if(browser.getTitle().equals("Home Page")){
            return States.Home_Page;
        }
        else if(browser.getTitle().equals("Contact Supervisor")){
            return States.Contact_Supervisor;
        }
        else if(browser.getTitle().equals("Login Key Request")){
            return States.Login_Key_Message_Page;
        }
        else if(browser.getTitle().equals("Login Page")){
            return States.Login_Page;
        }
        else if(browser.getTitle().equals("Messaging Page")){
            return States.Messaging_Page;
        }
        else if(browser.getTitle().equals("Automatic Logout")){
            return States.Automatic_Logout;
        }
        else{
            return null;
        }
    }

    // Method to reset the model
    public void reset(boolean b) {
        modelState = States.Home_Page;
        proceeding_to_homepage = true;
        proceeding_to_loginpage = false;
        proceeding_to_messagingpage = false;
        contacting_supervisor = false;
        invalid_agent_details = false;
        valid_agent_details = false;
        invalid_login_details = false;
        valid_login_details = false;
        submitting_valid_message = false;
        submitting_invalid_message = false;
        getting_next_message = false;
        getting_message_count = false;
        automatically_loggingout = false;
        logging_out = false;

        // Check boolean value passed
        if(b){
            browser.get("localhost:8080/");
        }
    }

    // ContactSupervisor Guard
    public boolean contactSupervisorGuard(){
        return getState().equals(States.Contact_Supervisor);
    }

    // Action method for ContactSupervisor
    public @Action void contactSupervisor(){
        browser.findElement(By.name("contactButton")).click();
        contacting_supervisor = true;
        modelState = States.Contact_Supervisor;
        assertEquals("The model's Contact_Supervisor state does not match the SUT's state.", contacting_supervisor, browser.getTitle().equals("Contact Supervisor"));
    }

    // LoginKeyMessagePage Guard
    public boolean loginKeyMessagePageGuard(){
        return getState().equals(States.Login_Key_Message_Page);
    }

    // Action method for LoginKeyMessagePage
    public @Action void loginKeyMessagePage(){
        browser.findElement(By.name("id")).sendKeys("001");
        browser.findElement(By.name("name")).sendKeys("Ryan Falzon");
        browser.findElement(By.name("getKeyButton")).click();
        valid_agent_details = true;
        modelState = States.Login_Key_Message_Page;
        assertEquals("The model's Login_Key_Message_Page state does not match the SUT's state.", valid_agent_details, browser.getTitle().equals("Login Key Request"));
    }

    @Test
    public void TelephoneSystemModelRunner() {
        final Tester tester = new GreedyTester(new ModelTest());
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
