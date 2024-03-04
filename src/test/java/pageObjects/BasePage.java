package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {

	WebDriver driver;

	public BasePage(WebDriver driver) {
		this.driver = driver;
		//to manage POM design patter
		PageFactory.initElements(driver, this); 
	}
}
