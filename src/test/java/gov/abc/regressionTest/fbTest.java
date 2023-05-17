package gov.abc.regressionTest;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import gov.abc.pages.HomePage;
import utilities.CommonUtils;
import utilities.ExcelUtils;
import utilities.JSWait;

public class fbTest {
	
	public WebDriver driver;
	String className = fbTest.class.getName();
	Logger log = Logger.getLogger(fbTest.class.getName());
	
	
	CommonUtils common = new CommonUtils();
	ExcelUtils excelFile = new ExcelUtils();
	HomePage homePage;
	
	

	
	
	
	@BeforeTest
	public void setUpBrowser() {
		driver=common.launchBrowser("TEST_ENV");
		System.out.println("Launching the Browser");
		log.info("Launching the Browser");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		homePage = PageFactory.initElements(driver, HomePage.class);
		JSWait.setDriver(driver);
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
}
	
	@Test(dataProvider = "dataProvider")
	public void myTest(String userName,String password) {
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
			System.out.println("My user name is "+ userName );
			System.out.println("My pass is "+ password);
			homePage.logInToFb(userName, password);
			
			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@DataProvider
	public Object[][] dataProvider(){
		String testDataPath = common.getProperty("myFirstTestData");
		String testSheet = "sheet2";
		Object[][] objArray =  excelFile.dataSource(testDataPath, testSheet, 1, 0);
		System.out.println("dataArray lenth is "+objArray.length);
		System.out.println("\n");
		return (objArray);
		
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

