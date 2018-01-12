package AutomatedWebTesting.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ContactSupervisorForm extends Form{

    // Constructor
    public ContactSupervisorForm(WebDriver browser){
        super(browser);
    }

    // Method to go to the Contact Supervisor page
    public void goTo(){
        find("contactButton").click();
    }

    // Method to populate the contact supervisor form
    public void populate(String agentID, String agentName){
        find("id").sendKeys(agentID);
        find("name").sendKeys(agentName);
    }

    // Method to submit the form
    public void submit(){
        find("getKeyButton").click();
    }

    // Method to automatically get a login key
    public void getKey(String agentID, String agentName){
        goTo();
        populate(agentID, agentName);
        submit();
    }

    // method to go back to the home page
    public void back(){
        find("backButton").click();
    }
}
