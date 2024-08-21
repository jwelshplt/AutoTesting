package AutoTestPLT.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	WebDriver driver;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id="onetrust-accept-btn-handler")
	WebElement oneTrust;

	public void oneTrustClick() throws InterruptedException {
		Thread.sleep(2000);
		oneTrust.click();
	}

}