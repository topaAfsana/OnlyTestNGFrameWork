package utilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class JSWait {

	private static WebDriver jsWaitDriver;
	private static WebDriverWait jsWait;
	private static JavascriptExecutor jsExec;
	
	// Get Driver from relevant test
	public static void setDriver(WebDriver driver) {
		jsWaitDriver = driver;
//		jsWait = new WebDriverWait(jsWaitDriver,10);
		jsExec = (JavascriptExecutor)jsWaitDriver;
		
	}
	
	
	
	
	
	
	
	
	
	
	
}
