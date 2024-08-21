package AutoTestPLT.pageobjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import dev.failsafe.internal.util.Assert;

public class CheckoutPaymentInfo {

	WebDriver driver;

	public CheckoutPaymentInfo(WebDriver driver) {
		this.driver = driver;
	}

	String selectedItem;
	String selectedSize;
	String cartSubTotal;
	String miniBagitem;
	String miniBagSize;
	String miniBagSubTotal;

	public String miniItem(String selectedItem) throws InterruptedException {
		Wait<WebDriver> wait2 = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait2.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//*[@id=\"app\"]/div/div/div/div[2]/main/div/div[2]/div/aside/div[1]/div[3]/ul/li[2]")));
		Thread.sleep(6000);
		String miniBagitem = driver.findElement(By.xpath("//li[@class='bag-item__name']")).getText();

		Assert.isTrue(true, miniBagitem, selectedItem);

		return miniBagitem;

	}

	public String miniSize(String selectedSize) throws InterruptedException {
		Wait<WebDriver> wait2 = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait2.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//*[@id=\"app\"]/div/div/div/div[2]/main/div/div[2]/div/aside/div[1]/div[3]/ul/li[2]")));
		Thread.sleep(6000);
		String miniBagSize = driver
				.findElement(By.xpath(
						"//*[@id=\"app\"]/div/div/div/div[2]/main/div/div[2]/div/aside/div[1]/div[2]/ul/li/ul/li[2]"))
				.getText().split(":")[1].trim();


		Assert.isTrue(true, miniBagSize, selectedSize);

		return miniBagSize;
		
	}

	public String miniSub(String cartSubTotal) throws InterruptedException {
		Wait<WebDriver> wait2 = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait2.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//*[@id=\"app\"]/div/div/div/div[2]/main/div/div[2]/div/aside/div[1]/div[3]/ul/li[2]")));
		Thread.sleep(6000);
		String miniBagSubTotal = driver.findElement(By.xpath("//li[@class='bag__totals-item subtotal']/span[2]"))
				.getText();

		Assert.isTrue(true, miniBagSubTotal, cartSubTotal);
		
		return miniBagSubTotal;

	}

	public Boolean minibagDelivery() {

		WebElement miniBagDelivery = driver.findElement(By.xpath("//li[@class='bag__totals-item']"));
		Boolean miniBagDeliveryCheck = miniBagDelivery.isDisplayed();
		return miniBagDeliveryCheck;

	}
	
	public Boolean minibagGT() {

		WebElement miniBagGT = driver.findElement(By.xpath("//li[@class='bag__totals-item grand-total']"));
		Boolean miniBagGTCheck = miniBagGT.isDisplayed();
		return miniBagGTCheck;
	}

	public void scrollToPayment() {
		WebElement cardButton = driver
				.findElement(By.xpath("//*[@id=\"worldpay-container\"]/div[1]/div/div/div/button"));
		new Actions(driver).scrollToElement(cardButton).perform();

	}

	public Boolean selectCard() {
		Wait<WebDriver> wait2 = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait2.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//*[@id=\"worldpay-container\"]/div[1]/div/div/div/button")));
		driver.findElement(By.xpath("//*[@id=\"worldpay-container\"]/div[1]/div/div/div/button")).click();
		wait2.until(ExpectedConditions.visibilityOfElementLocated(By.className("box__content-container")));
		WebElement cardContainer = driver.findElement(By.className("box__content-container"));
		Boolean cardDisplay = cardContainer.isDisplayed();
		return cardDisplay;

	}
}
