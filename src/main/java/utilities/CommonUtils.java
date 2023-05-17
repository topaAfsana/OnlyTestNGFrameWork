package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Random;

import org.apache.log4j.Logger;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class CommonUtils {
	
	public CommonUtils() {
		
	}
	
	String className = CommonUtils.class.getName();
	Logger log = Logger.getLogger(CommonUtils.class.getName());
	
	/*
	 * Purpose: Set Environment , Launch Chrome browser and trigger URL related to the selected environment.
	 * Dependencies: Property file has environments listed - getProperty() method; launchChrome() method 
	 * Parameters: Environment text
	 * Return WebDriver
	 */
	public WebDriver launchBrowser(String Env) {
		String URL = "";
		WebDriver Driver = null;
		try {
			switch(Env.toUpperCase()) {
			case "TEST_ENV":
				URL = getProperty("test-url");
				break;
			case "DEV":
				URL = getProperty("dev-url");
				break;
			default:
				URL = getProperty("env");
				
			}
			Driver = launchChrome();
			launchURL(Driver,URL);
			System.out.println("Environment: " + Driver.getCurrentUrl());
			log.info("Environment: " + Driver.getCurrentUrl());
			
		} catch(Exception e) {
			log.info("---------- OBSERVED EXCEPTION ------: in method pageaunch() \n " + e);
		}
		return Driver;
	}
	
	public WebDriver launchChrome() {
		String Directory = "";
		String WorkingDir = System.getProperty("user.dir");
		String Drv = getProperty("chromeDriver");
		Directory = WorkingDir + Drv;
		WebDriver Driver = null;
		try {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("disable-infobars");
			System.setProperty("webdriver.chrome.driver",Directory);
			Driver = new ChromeDriver(options);
			
		}catch(Exception e) {
			Log(e +"---------- OBSERVED EXCEPTION ---------- : in method Chrome(directory, URL)");
		}
		
		return Driver;
	}
	
	
	
	
	
//	===================================
	public void launchURL(WebDriver pDriver, String URL) {
			try{
				pDriver.get(URL);
				Log(pDriver.getTitle());
		}
			catch(Exception e) {
			Log(e + "-------OBSERVED EXCEPTION -----------: in method Chrome(directory, URL)");
		}
	}
	/* purpose: to get property value
	 * pre-condition: property file should exist in lib folder
	 * input parameter: property key
	 * output: property value.
	 * 
	 */
	
	public String getProperty(String pKey) {
		String lvVal = "";
		try {
			Properties prop = new Properties();
			String workingDir = System.getProperty("user.dir");
			String fileName = "\\resources\\config.properties";
			prop = propertyFileInputStream(workingDir+fileName);
			
			if (prop.containsKey(pKey)) {
				lvVal = prop.getProperty(pKey);
			}else {
				Log("\nProperty key " + pKey + "doest not exist in the file. \n");
				Log("\nFollowing are the available keys in OR:\n");
				
				@SuppressWarnings("rawtypes")
				Enumeration allKeys = prop.keys();
				while(allKeys.hasMoreElements()) {
					String ck = (String)allKeys.nextElement();
					String val = prop.getProperty(ck);
					log.info(ck + "~" + val);
				}
			}
		}
		catch(Exception e) {
			Log("-------- OBSERVED EXCEPTION -------: " +e + "in method getProperty(pString pStr)");
			e.printStackTrace();
		}
		return lvVal;
	}
	
	public Properties propertyFileInputStream(String pStr) {
		Properties lvRtrnString = null;
		try {
			Properties prop = new Properties();
			InputStream input = null;
			input = new FileInputStream(pStr);
			
			//Load properties
			prop.load(input);
			input.close();
			lvRtrnString = prop;
		}
		catch(Exception e) {
			Log("-------- OBSERVED EXCEPTION ---------- : " + e + 
					" in method fileInputStream(pQry)");
		}
		return lvRtrnString;
	}
	
	public void Log(String pInputTxt) {
		
	}
	
	// Verify content of PDF FILE
	public void verifyContentInPDF() throws IOException {
		// SPECFY THE PATH OF PDF FILE
		FileInputStream doc = new FileInputStream("");
		PDDocument pdfDoc = PDDocument.load(doc);
		String pdfContent = new PDFTextStripper().getText(pdfDoc);
		System.out.println("Text displayed as : "+ pdfContent);
		Assert.assertTrue(pdfContent.contains("Text of that content"));
	}
	
	public Collection<?> getSortedList(ArrayList<String> pList){
		try {
			pList.sort(Comparator.naturalOrder());
		}catch(Exception e) {
			e.printStackTrace();
			log.info("--------- OBSERVED EXCEPTION ------------- : " + e +
					"in methohd getSortedList()");
		}
		return pList;
	}

	public int getRandomNumber(int Min, int Max) {
		int rtrnInt = 0;
		try {
			Random random = new Random();
			rtrnInt = random.ints(Min, (Max+1)).findFirst().getAsInt();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return rtrnInt ;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
