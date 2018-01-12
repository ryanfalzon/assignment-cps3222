package AutomatedWebTesting.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Form {

    // Properties
    WebDriver browser;

    // Constructor
    public Form(WebDriver browser){
        this.browser = browser;
    }

    // Method to get the current page title
    public String getPageTitle(){
        return browser.getTitle();
    }

    // Method to return a web element
    public WebElement find(String name){
        return browser.findElement(By.name(name));
    }
}
