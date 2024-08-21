package AutoTestPLT.pageobjects;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class PLP {

	WebDriver driver;

	public PLP(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(className="w-full")
	WebElement productList;
	
	Boolean PDPCheck;

	public Boolean pageCheck() throws InterruptedException {

		Boolean PDPCheck = driver.findElement(By.className("w-full")).isEnabled();
		return PDPCheck;
	}

	public void ProductClick() {

		int productCount = productList.findElements(By.xpath("//div[@role='listitem']")).size();
		int randomProduct = selectRandomProduct(productCount);
		driver.findElement(By.xpath("//div[@role='listitem'][" + randomProduct + "]")).click();

	}

	public static int selectRandomProduct(int ProductCount) {

		Random r = new Random();
		int rNum = r.nextInt(ProductCount);
		int returnNum = rNum + 1;
		return returnNum;
	}

}
