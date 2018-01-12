package AutomatedWebTesting.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginForm extends Form {

    // Constructor
    public LoginForm(WebDriver browser){
        super(browser);
    }

    // Method to go to the Login page
    public void goTo(){
       find("loginButton").click();
    }

    // Method to populate the login form
    public void populate(String agentID, String loginKey){
        find("id").sendKeys(agentID);
        find("loginkey").sendKeys(loginKey);
    }

    // Method to submit the form
    public void submit(){
        find("loginbutton").click();
    }

    // Method to automatically login the agent
    public void login(){
        goTo();
        submit();
    }

    // Method to go back to the home page
    public void back(){
        find("backButton").click();
    }
}
