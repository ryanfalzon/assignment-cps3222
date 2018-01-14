package MobileTesting.MobilePageObjects;

import io.appium.java_client.android.AndroidDriver;

public class MobileContactSupervisorForm extends MobileForm {

    // Constructor
    public MobileContactSupervisorForm(AndroidDriver device){
        super(device);
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
