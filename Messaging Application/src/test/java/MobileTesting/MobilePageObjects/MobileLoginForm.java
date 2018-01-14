package MobileTesting.MobilePageObjects;

import AutomatedWebTesting.PageObjects.Form;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.RemoteWebElement;

public class MobileLoginForm extends MobileForm {

    // Constructor
    public MobileLoginForm(AndroidDriver device){
        super(device);
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