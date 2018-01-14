package MobileTesting.MobilePageObjects;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebElement;

public class MobileForm {

    // Properties
    protected AndroidDriver device;

    // Constructor
    public MobileForm(AndroidDriver device){
        this.device = device;
    }

    // Method to get the current page title
    public String getPageTitle(){
        return device.getTitle();
    }

    // Method to return a web element
    public RemoteWebElement find(String name){
        return (RemoteWebElement)device.findElement(By.name(name));
    }
}