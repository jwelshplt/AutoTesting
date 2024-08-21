package AutoTestPLT.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class CartPage {

	WebDriver driver;

	public CartPage(WebDriver driver) {
		this.driver = driver;
	}
	
	String selectedSize;
	String selectedItem;
	String cartSizeCut;
	String cartItem;
	String cartSubTotal;

	public String cartSize(String selectedSize) {

		WebElement cartBox = driver.findElement(By.className("grid"));
		String cartSize = cartBox.findElement(By.xpath("//li[contains(@class, 'grid')]/div[3]/ul/li[1]/span"))
				.getText();
		String cartSizeCut = cartSize.split(":")[1].trim();
		
		return cartSizeCut;

	}

	public String cartItemName(String selectedItem) {

		WebElement cartBox = driver.findElement(By.className("grid"));
		String cartItem = cartBox.findElement(By.xpath("//li[contains(@class, 'grid')]/div/a")).getText();
		
		return cartItem;

	}

	public String cartSubtotal() {

		String cartSubTotal = driver.findElement(By.xpath("//*[@id=\"cart-subtotal\"]/div/div/p[2]")).getText();
		return cartSubTotal;
	}

	public void proceedToCheckout() {

		driver.findElement(By.id("checkout-button-bottom")).click();
	}
}
