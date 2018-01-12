package AutomatedWebTesting.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MessagingForm extends Form {

    // Constructor
    public MessagingForm(WebDriver browser){
        super(browser);
    }

    // Method to populate the messaging form
    public void populate(String targetAgent, String message){
        find("targetagent").sendKeys(targetAgent);
        find("message").sendKeys(message);
    }

    // Method to submit the form
    public void submit(){
        find("submitmessage").click();
    }

    // Method to automatically send a message
    public void sendMessage(String targetAgent, String message){
        populate(targetAgent, message);
        submit();
    }

    // Method to get the next message
    public void nextMessage(){
        find("next").click();
    }

    // Method to check if the mailbox has messages
    public void hasMessaage(){
        find("count").click();
    }

    // Method to go back to the home page
    public void back(){
        find("logoutButton").click();
    }
}
