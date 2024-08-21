package AutoTestPLT.pageobjects;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PDP {

	WebDriver driver;

	public PDP(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}	
	
	@FindBy(className="mb-2")
	WebElement sizeGuide;
	
	@FindBy(id="pdp-name")
	WebElement itemName;
	
	@FindBy(id="add-to-bag-button")
	WebElement AtbButton;
	
	@FindBy(xpath="//div/div/a[contains(@href,'/checkout/cart')]")
	WebElement GtcButton;
	
	int randomSize;
	String selectedSize;
	String selectedItem;
	
	public int clickSize() {

		// selecting size at random
		//List<WebElement> sizes = sizeGuide.findElements(By.xpath("//div/div/button[contains(@class, 'h-8')]"));
		int sizesCount = driver.findElements(By.xpath("//div/div/button[contains(@class, 'h-8')]")).size();
		int randomSize = selectRandomSize(sizesCount);
		driver.findElement(By.xpath("//div/div/button[contains(@class, 'h-8')][" + randomSize + "]")).click();
		return randomSize;
	}
	public String grabProductSize(int randomSize) {
		WebElement sizeGuide = driver.findElement(By.className("mb-2"));
		//List<WebElement> sizes = sizeGuide.findElements(By.xpath("//div/div/button[contains(@class, 'h-8')]"));
		String selectedSize = sizeGuide
				.findElement(By.xpath("//div/div/button[contains(@class, 'h-8')][" + randomSize + "]")).getText();
		return selectedSize;

	}

	public String grabProductName() {

		String selectedItem = itemName.getText();
		return selectedItem;

	}

	public void addToBag() throws InterruptedException {
		
		Thread.sleep(5000);
		Boolean addToBagCheck = driver.findElement(By.id("add-to-bag-button")).isEnabled();
		
		if (addToBagCheck == false){
			
			clickSize();
		}
		
		AtbButton.click();

	}

	public void goToBag() {

		GtcButton.click();

	}

	public static int selectRandomSize(int sizesCount) {

		Random r = new Random();
		int rNum = r.nextInt(sizesCount);
		int returnNum = rNum + 1;
		return returnNum;

	}

}

