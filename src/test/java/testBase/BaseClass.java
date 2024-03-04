package testBase;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
	public static WebDriver driver;
	public static Properties p;
	
	
	@BeforeClass()
	@Parameters({"os","browser"})
	public void setup(String os,String br) throws IOException {
		
		FileReader file=new FileReader("C:\\Users\\2308898\\eclipse-workspace\\MakeMyTrip_2\\files\\config.properties");
		 p=new Properties();
		 p.load(file);
		 

			if(p.getProperty("execution_environment").equalsIgnoreCase("remote"))
			 	{	
				
				DesiredCapabilities capabilities=new DesiredCapabilities();
				
				//os
				if(os.equalsIgnoreCase("windows"))
				{
					capabilities.setPlatform(Platform.WIN11);
				}
				else if(os.equalsIgnoreCase("mac"))
				{
					capabilities.setPlatform(Platform.MAC);
				}
				else
				{
					System.out.println("No matching os..");
					return;
				}
				
				//browser
				switch(br.toLowerCase())
				{
				case "chrome" : 
					capabilities.setBrowserName("chrome");
					break;
				case "edge" : 
					capabilities.setBrowserName("MicrosoftEdge");
					break;
				default: 
					System.out.println("No matching browser.."); 
					return;
				}
				
				driver = new RemoteWebDriver(new URL("http://192.168.104.125:4444"), capabilities);
				
			    }

		if(p.getProperty("execution_environment").equalsIgnoreCase("local"))
		{
			//launching browser based on condition 
		switch(br.toLowerCase()) {
		case "chrome": 
			driver=new ChromeDriver();
			break;
		case "edge":
			driver=new EdgeDriver();
			break;
		default: 
			System.out.println("No matching browser");
			return;
		}
		}
		
		// driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		// driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		driver.get(p.getProperty("appURL"));
		driver.manage().window().maximize();
	}

	public void captureScreenShot(String user) throws IOException {
		WebElement specific_location = driver.findElement(By.xpath("//div[@class='deliver__content']"));
		File src = specific_location.getScreenshotAs(OutputType.FILE);
		File trg = new File(
				"C:\\Users\\2308898\\eclipse-workspace\\MakeMyTrip_2\\ScreenShots\\"+user+".png");
		FileUtils.copyFile(src, trg);
	}
	
	public void refresh()
	{
		driver.navigate().refresh();
	}

	@AfterClass()
	public void teardown() {
		driver.quit();
	}
}
