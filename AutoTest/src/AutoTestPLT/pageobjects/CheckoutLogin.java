package AutoTestPLT.pageobjects;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutLogin {
	
	WebDriver driver;
	
	public CheckoutLogin(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//input[@id='customer-email']")
	WebElement emailBox;
	
	
	@FindBy(xpath="//button[@type='submit']")
	WebElement submitButton;
	
	public void loginDetails() throws IOException {
		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+
				"\\src\\AutoTestPLT\\resources\\GlobalData.properties");
		prop.load(fis);
		String username = prop.getProperty("Username");
		String password = prop.getProperty("Password");
		
		Wait<WebDriver> wait2 = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='customer-email']")));
		
		emailBox.sendKeys(username);
		submitButton.click();

		// enter password and select to continue
		
		wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='customer-password']")));
		WebElement passwordBox = driver.findElement(By.xpath("//input[@id='customer-password']"));
		passwordBox.sendKeys(password);
	}
	
	public void continueLogin() {
		
		submitButton.click();
	}

}
