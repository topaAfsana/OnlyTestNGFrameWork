package gov.abc.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class HomePage {
	
	public WebDriver driver;
	public HomePage(WebDriver driver) {
		this.driver = driver;
	}
	
	@FindBy(how = How.NAME, using = "q")
	public WebElement searchBox;
	
	
	@FindBy(how = How.NAME, using = "email")
	public WebElement emailBox;
	
	@FindBy(how = How.NAME, using = "pass")
	public WebElement passBox;
	
	@FindBy(how = How.NAME, using = "login")
	public WebElement logIn;
	

	
	public void logInToFb(String userName,String password) {
		emailBox.sendKeys(userName);
		passBox.sendKeys(password);
		logIn.click();
	}
	
	
	public void search(String searchText) {
		searchBox.sendKeys(searchText);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
}
