package gov.abc.regressionTest;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import gov.abc.driverScript.GlobalVariables;
import gov.abc.pages.HomePage;
import utilities.CommonUtils;
import utilities.ExcelUtils;
import utilities.JSWait;

public class myFirstTest {

	WebDriver driver;
	String className = myFirstTest.class.getName();
	Logger log = Logger.getLogger(myFirstTest.class.getName());
	
	
	CommonUtils common = new CommonUtils();
	ExcelUtils excelFile = new ExcelUtils();
	HomePage homePage;
	
	
	@DataProvider
	public Object[][] dataProvider(){
		String testDataPath = common.getProperty("myFirstTestData");
		String testSheet = "sheet1";
		Object[][] objArray =  excelFile.dataSource(testDataPath, testSheet, 1, 0);
		System.out.println(objArray.length);
		System.out.println("\n");
		return (objArray);
		
	}
	
	
	
	@BeforeTest
	public void setUpBrowser() {
		driver=common.launchBrowser("TEST_ENV");
		log.info("Launching the Browser");
		homePage = PageFactory.initElements(driver, HomePage.class);
		JSWait.setDriver(driver);
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
}
	
	@Test(dataProvider = "dataProvider")
	public void myTest(String searchText) {
		log.info("****************************************************************");
		log.info("Test name: My Home page");
		log.info("Test type: MustPass");
		log.info("****************************************************************");
		/*
		//json logging variables
		GlobalVariables.projCurrentStepName = "myFirstTest";
		GlobalVariables.projectCurrentUSName = "US12345";
		GlobalVariables.projectCurrentTCName = "TC1234, TC2345";
		
		
		*/
		
		try {
			driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
			log.info("Page Load");
//			driver.manage().window().maximize();
			log.info("Maximize the window");
			
			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
			System.out.print("MY SEARCHED TEXT "+searchText);
			homePage.search(searchText);
			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	@AfterMethod
	public void genarateReport(ITestResult result) {
		report.testScenarioExecutionResult(result);
		report.setPassFailResult();
	}
	*/
	
/*	
	@AfterTest
	public void closeBrowser() {
		driver.quit();
	}
	
	*/
}
