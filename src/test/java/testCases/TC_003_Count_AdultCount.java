package testCases;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.SearchPage;
import testBase.BaseClass;

public class TC_003_Count_AdultCount extends BaseClass {
	@Test(enabled = true,groups= {"smoke"})
	public void adultCount() throws InterruptedException { 
		//extract all the numbers for Adult persons and store in a List
		SearchPage sp = new SearchPage(driver);
		Thread.sleep(5000);
		 
		String handle = driver.getWindowHandle();
 
		driver.switchTo().frame(sp.frame_handle());
 
		sp.click_popup_dismiss();
 
		driver.switchTo().window(handle);
		Thread.sleep(2000);
		//clicking the hotel icon
		sp.click_hotel_tab();
		//clicking guests and rooms option
		sp.click_guest();
		//printing total Adult count
		List<WebElement> Adults=sp.getAdultCount();
		System.out.println("All the adults number: ");
		for(int i=0;i<Adults.size();i++) {
			System.out.print(Adults.get(i).getText()+" ");
		}
//		System.out.println("Total Adult Count :" + sp.getAdultCount());
//		//converting into String 
//		String actual_value = Integer.toString(sp.getAdultCount());
//		sp.click_guest_apply();
//		Assert.assertEquals(actual_value, "40");
	}
}