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
		SearchPage sp = new SearchPage(driver);
		sp.ScrollTillGiftCardTab();
		Thread.sleep(3000);
		sp.click_gift_card();
		Set<String> win_handles = driver.getWindowHandles();
		List<String> win_handles_list = new ArrayList<String>(win_handles);
		GiftCardsPage gcp = new GiftCardsPage(driver.switchTo().window(win_handles_list.get(1)));
		Thread.sleep(2000);
		gcp.ScrollTillBirthDayCardTab();
		Thread.sleep(3000);
		gcp.click_birthday_gift_card();
		ExcelUtility eu=new ExcelUtility("C:\\Users\\2308898\\eclipse-workspace\\MakeMyTrip_2\\ExcelSheets\\User_Credential.xlsx");
		int row_num=eu.getRowCount("Sheet1");
		for(int i=1;i<=row_num;i++)
		{
			gcp.setName(eu.getCellData("Sheet1", i, 0));
			String name=eu.getCellData("Sheet1", i, 0).toString();
			gcp.setMobileNumber(eu.getCellData("Sheet1", i, 1));
			gcp.setEmailId(eu.getCellData("Sheet1", i, 2));
			gcp.clickBuy();
			Thread.sleep(3000);
			String message = gcp.getErrorEmailMessage();
			captureScreenShot(name);
			Assert.assertEquals(message, "Please enter a valid Email id.");
			refresh();
		}
	}
}
