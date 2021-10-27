package gov.abc.regressionTest;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import gov.abc.pages.HomePage;
import utilities.CommonUtils;
import utilities.JSWait;

public class myFirstTest {

	WebDriver driver;
	String className = myFirstTest.class.getName();
	Logger log = Logger.getLogger(myFirstTest.class.getName());
	
	
	CommonUtils common = new CommonUtils();
	HomePage homePage;
	
	@BeforeTest
	public void setUpBrowser() {
		driver=common.launchBrowser("TEST_ENV");
		log.info("Launching the Browser");
		homePage = PageFactory.initElements(driver, HomePage.class);
		JSWait.setDriver(driver);
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
}
	
	@Test
	public void myTest() {
		try {
			driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
			log.info("Page Load");
			driver.manage().window().maximize();
			log.info("Maximize the window");
			
			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
			homePage.search();
			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@AfterTest
	public void closeBrowser(ITestResult result) {
		driver.quit();
	}
	
	
}
