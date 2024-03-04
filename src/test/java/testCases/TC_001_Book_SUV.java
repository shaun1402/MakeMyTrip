package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.BookCabPage;
import pageObjects.SearchPage;
import testBase.BaseClass;

public class TC_001_Book_SUV extends BaseClass {
	@Test(enabled = true,groups= {"smoke"})
	public void search() throws InterruptedException { //perform search for the minimum price of SUV cab
		
		SearchPage sp = new SearchPage(driver);
		BookCabPage bcp = new BookCabPage(driver);
		Thread.sleep(5000);
		 
		String handle = driver.getWindowHandle();
 
		driver.switchTo().frame(sp.frame_handle());
 
		sp.click_popup_dismiss();
 
		driver.switchTo().window(handle);
		//click on cab symbol from the search page 
		sp.clickcab();
		//sending value into the "from" input box 
		sp.setFromTab(p.getProperty("from"));
		Thread.sleep(3000);
		//click the first suggestion from the dropdown 
		sp.click_first_from_suggestion();
		//sending value into the "to" input box 
		sp.setToTab(p.getProperty("to"));
		Thread.sleep(2000);
		//click the first suggestion from the dropdown 
		sp.click_first_to_suggestion();
		//selecting any future date 
		sp.setStartDate();
		//setting time to 6:30 AM 
		sp.SetTime();
		
		//after choosing all the inputs its clicking search button
		sp.clickSearch();  //it will redirect to a another page
		
		//validating if it is going to next page or not
		Assert.assertEquals("Select Filters", bcp.validatePage());
		//select SUV from the cab type filters 
		bcp.click_checkbox_of_SUV();
		//scroll until its finds the lowest price of the SUV
		bcp.ScrollTillLowestSUVTab();
		Thread.sleep(2000);
		//printing minimum price of SUV in the console
		System.out.println("MINIMUM PRICE OF SUV :" + bcp.minimum_price());
	}
}
