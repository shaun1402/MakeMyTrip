package testCases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.GiftCardsPage;
import pageObjects.SearchPage;
import testBase.BaseClass;
import utilities.ExcelUtility;

public class TC_002_Gift_Card extends BaseClass {
	@Test(enabled =true,groups={"regression"})
	public void enter_wrong_info() throws InterruptedException, IOException {
		//will enter gift card details with a invalid email and capture the error mssg
		SearchPage sp = new SearchPage(driver);
		Thread.sleep(5000);
		 
		String handle = driver.getWindowHandle();
 
		driver.switchTo().frame(sp.frame_handle());
 
		sp.click_popup_dismiss();
 
		driver.switchTo().window(handle);
		//scroll to gift card  from the search page
		sp.ScrollTillGiftCardTab();
		Thread.sleep(3000);
		
		//clicking gift card 
		sp.click_gift_card(); //redirect to the gift card page in a new tab 
		
		//handling the windows 
		Set<String> win_handles = driver.getWindowHandles();
		List<String> win_handles_list = new ArrayList<String>(win_handles);
		GiftCardsPage gcp = new GiftCardsPage(driver.switchTo().window(win_handles_list.get(1)));
		Thread.sleep(2000);
		//scroll till birthday card tab 
		gcp.ScrollTillBirthDayCardTab();
		Thread.sleep(3000);
		//clicking the birthday card element
		gcp.click_birthday_gift_card();
		
		//passing the path of the excel sheet from to take inputs
		ExcelUtility eu=new ExcelUtility("C:\\Users\\2308898\\eclipse-workspace\\MakeMyTrip_2\\ExcelSheets\\User_Credential.xlsx");
		//storing the total number of rows by calling the getRowCount method from ExcelUtility
		int row_num=eu.getRowCount("Sheet1");
		//iterating through the rows 
		for(int i=1;i<=row_num;i++)
		{
			//sending name to GiftCardPage from excel
			gcp.setName(eu.getCellData("Sheet1", i, 0));
			//storing the value of name into a String 
			String name=eu.getCellData("Sheet1", i, 0).toString();
			//sending phone number to mobile number input box 
			gcp.setMobileNumber(eu.getCellData("Sheet1", i, 1));
			//sending email Id to email Id input box 
			gcp.setEmailId(eu.getCellData("Sheet1", i, 2));
			//clicking buy
			gcp.clickBuy();
			Thread.sleep(3000);
			String message = gcp.getErrorEmailMessage();
			captureScreenShot(name);
			//printing the error message in the console
			System.out.println(message);
			
			//validating the error message 
			Assert.assertEquals(message, "Please enter a valid Email id.");
			//refreshing the page 
			refresh();
		}
	}
}
