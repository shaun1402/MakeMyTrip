package pageObjects;

import java.util.List;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchPage extends BasePage {
	public SearchPage(WebDriver driver) {
		super(driver);
	}
	//Elements For TC_001_Book_SUV
	@FindBy(xpath = "//*[@id='SW']/div[1]/div[2]/div/div/nav/ul/li[7]/span/a/span[2]")
	WebElement cab_tab;

	@FindBy(id = "fromCity")
	WebElement from_label;

	@FindBy(xpath = "//input[@placeholder='From']")
	WebElement from_text_box;

	@FindBy(id = "react-autowhatever-1-section-0-item-0")
	WebElement from_suggestion;
	
	@FindBy(xpath = "//input[@placeholder='To']")
	WebElement to_text_box;

	@FindBy(xpath = "//*[@id=\"react-autowhatever-1-section-0-item-0\"]/div/p/span")
	WebElement to_suggestion;

	@FindBy(xpath = "//div[contains(@aria-label,'Mar 13 2024')]")
	WebElement fromdate;
	
	@FindBy(xpath = "//li[@class='hrSlotItemParent']//span[contains(text(),'06')]")
	WebElement pickup_hour;

	@FindBy(xpath = "//li[@class='minSlotItemParent']//span[contains(text(),'30')]")
	WebElement pickup_minute;

	@FindBy(xpath = "//*[@id=\"top-banner\"]/div[2]/div/div/div[2]/div[1]/div[5]/div[1]/div[2]/ul[3]/li[1]")
	WebElement pickup_AM;

	@FindBy(xpath = "//*[@id=\"top-banner\"]/div[2]/div/div/div[2]/div[1]/div[5]/div[1]/div[1]/div[5]/span")
	WebElement apply;

	@FindBy(xpath = "//a[normalize-space()='Search']")
	WebElement search;
	
	@FindBy(id = "webklipper-publisher-widget-container-notification-frame")
	WebElement pop_frame;
 
	@FindBy(xpath = "//i[@class='wewidgeticon we_close']")
	WebElement frame_dismiss;
	

	//Element for TC_002_Gift_card
	@FindBy(xpath = "//li[@data-cy='tertiaryRowItem_Gift Cards']//div[@class='choosFrom__list--itemDesc makeFlex column flexOne']")
	WebElement gift_card;
	
	@FindBy(xpath = "//a[normalize-space()='Search']")
	WebElement giftTab;
	
	
	//Element for TC_003_Count_Adults

	@FindBy(xpath = "//span[@class='headerIconTextAlignment chNavText darkGreyText'][normalize-space()='Hotels']")
	WebElement hotel_tab;
	
	@FindBy(xpath = "/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/div[4]/label[1]/p[1]")
	WebElement guest_label;
	
	@FindBy(xpath = "//span[@data-testid='adult_count']")
	WebElement guest_dropdown;
	
	@FindBy(xpath = "//li[@data-cy='GuestSelect$$_323']")
	List<WebElement> adultcount;
	
	@FindBy(xpath = "//button[@data-cy='RoomsGuestsNew_327']")
	WebElement guestApply;

	
	//Methods for TC_001
	
	public WebElement frame_handle() {
		return pop_frame;
	}
 
	public void click_popup_dismiss() {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", frame_dismiss);
	}

	public void clickcab() {
		cab_tab.click();
	}

	public void setFromTab(String fromLocation) {
		from_label.click();
		from_text_box.sendKeys(fromLocation);

	}

	public void setToTab(String toLocation) {
		to_text_box.sendKeys(toLocation);

	}

	public void click_first_from_suggestion() {
		from_suggestion.click();
	}

	public void click_first_to_suggestion() {
		to_suggestion.click();
	}

	public void clickSearch() {
		search.click();
	}

	public void setStartDate() {
		fromdate.click();

	}

	public void SetTime() {
		// returnlabel.click();
		pickup_hour.click();
		pickup_minute.click();
		pickup_AM.click();
		apply.click();
	}

	//methods for TC_002
	public void click_gift_card() {
		gift_card.click();
	}

	public void click_guest() {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", guest_label);
		jse.executeScript("arguments[0].click();", guest_dropdown);
	}
	
	//Methods for TC_003
	public List<WebElement> getAdultCount() {
		return adultcount;
	}

	public void click_guest_apply() {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", guestApply);
	}

	public void click_hotel_tab() {
		hotel_tab.click();
	}

	public void ScrollTillGiftCardTab() {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView(true);", giftTab);
	}
}
