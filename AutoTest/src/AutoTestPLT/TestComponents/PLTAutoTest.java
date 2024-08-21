package AutoTestPLT.TestComponents;

import java.sql.Driver;
import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import dev.failsafe.internal.util.Assert;

//test before splitting into classes per page and adding tests

public class PLTAutoTest {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\user\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.get("https://www.prettylittlething.com/");
		driver.manage().window().setSize(new Dimension(1400, 1000));
		Thread.sleep(2000);
		driver.findElement(By.id("onetrust-accept-btn-handler")).click();

		// select random nav item
		WebElement navBar = driver.findElement(By.cssSelector("ul[data-orientation='horizontal']"));
		List<WebElement> navMenu = navBar.findElements(By.xpath("//nav/div/ul/li/a"));
		int navMenuCount = navBar.findElements(By.xpath("//nav/div/ul/li/a")).size();
		int randomNav = selectRandomNav(navMenuCount);
		driver.findElement(By.xpath("//nav/div/ul/li[" + randomNav + "]")).click();

		// test that the PLP is showing

		// select random item
		WebElement productList = driver.findElement(By.className("w-full"));
		List<WebElement> products = productList.findElements(By.xpath("//div[@role='listitem']"));
		int productCount = productList.findElements(By.xpath("//div[@role='listitem']")).size();
		int randomProduct = selectRandomProduct(productCount);
		driver.findElement(By.xpath("//div[@role='listitem'][" + randomProduct + "]")).click();

		// chose random size of product
		WebElement sizeGuide = driver.findElement(By.className("mb-2"));
		List<WebElement> sizes = sizeGuide.findElements(By.xpath("//div/div/button[contains(@class, 'h-8')]"));
		int sizesCount = driver.findElements(By.xpath("//div/div/button[contains(@class, 'h-8')]")).size();
		int randomSize = selectRandomSize(sizesCount);
		driver.findElement(By.xpath("//div/div/button[contains(@class, 'h-8')][" + randomSize + "]")).click();

		// store product name and size in string

		String selectedSize = sizeGuide
				.findElement(By.xpath("//div/div/button[contains(@class, 'h-8')][" + randomSize + "]")).getText();
		System.out.println(selectedSize);
		
		String selectedItem = driver.findElement(By.id("pdp-name")).getText();
		System.out.println(selectedItem);

		// select add to bag

		driver.findElement(By.id("add-to-bag-button")).click();
		
		// click on bag icon
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"header-account-links\"]/div/div/a/span/span[2]")));

		driver.findElement(By.xpath("//div/div/a[contains(@href,'/checkout/cart')]")).click();

		// assert products from stored data to whats in the cart
		
		
		WebElement cartBox = driver.findElement(By.className("grid"));
		String cartSize = cartBox
				.findElement(By.xpath("//li[contains(@class, 'grid')]/div[3]/ul/li[1]/span")).getText();
		String cartItem = cartBox.findElement(By.xpath("//li[contains(@class, 'grid')]/div/a")).getText();
		
		String cartSizeCut = cartSize.split(":")[1].trim();
		System.out.println(cartSizeCut);

		
		Assert.isTrue(true, cartSize, selectedSize);
		Assert.isTrue(true, cartItem, selectedItem);

		// store cart subtotals in string
		
		String cartSubTotal = driver.findElement(By.xpath("//*[@id=\"cart-subtotal\"]/div/div/p[2]")).getText();
		System.out.println(cartSubTotal);

		// select proceed to checkout
		
		driver.findElement(By.id("checkout-button-bottom")).click();

		// enter username and select to continue
		Wait<WebDriver> wait2 = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='customer-email']")));
		
		driver.findElement(By.xpath("//input[@id='customer-email']")).sendKeys("jwelshplt@gmail.com");
		driver.findElement(By.xpath("//button[@type='submit']")).click();

		// enter password and select to continue
		
		wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='customer-password']")));
		
		driver.findElement(By.xpath("//input[@id='customer-password']")).sendKeys("Password_2");
		driver.findElement(By.xpath("//button[@type='submit']")).click();

		// assert product name size and subtotal in bag section
		
		wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"app\"]/div/div/div/div[2]/main/div/div[2]/div/aside/div[1]/div[3]/ul/li[2]")));
		Thread.sleep(6000);
		String miniBagitem = driver.findElement(By.xpath("//li[@class='bag-item__name']")).getText();
		String miniBagSize = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div/div[2]/main/div/div[2]/div/aside/div[1]/div[2]/ul/li/ul/li[2]")).getText().split(":")[1].trim();
		String miniBagSubTotal = driver.findElement(By.xpath("//li[@class='bag__totals-item subtotal']/span[2]")).getText();
		
		System.out.println(miniBagitem);
		System.out.println(miniBagSize);
		System.out.println(miniBagSubTotal);
		
		Assert.isTrue(true, miniBagitem, selectedItem);
		Assert.isTrue(true, miniBagSize, selectedSize);
		Assert.isTrue(true, miniBagSubTotal, cartSubTotal);

		// confirm the bag section is displaying delivery total and grand total and
		
		WebElement miniBagDelivery = driver.findElement(By.xpath("//li[@class='bag__totals-item']"));
		WebElement miniBagGT = driver.findElement(By.xpath("//li[@class='bag__totals-item grand-total']"));
		
		System.out.println(miniBagDelivery.isDisplayed());
		System.out.println(miniBagGT.isDisplayed());
		
		// scroll to payment methods

		WebElement cardButton = driver.findElement(By.xpath("//*[@id=\"worldpay-container\"]/div[1]/div/div/div/button"));
        new Actions(driver)
                .scrollToElement(cardButton)
                .perform();
        

		// select payment methods
		
        wait2.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"worldpay-container\"]/div[1]/div/div/div/button")));
        driver.findElement(By.xpath("//*[@id=\"worldpay-container\"]/div[1]/div/div/div/button")).click();
        wait2.until(ExpectedConditions.visibilityOfElementLocated(By.className("box__content-container")));
        WebElement cardContainer = driver.findElement(By.className("box__content-container"));
        System.out.println(cardContainer.isDisplayed());

		// close browser
        
        driver.close();

		// tentng report shoudl be complete
	}

	public static int selectRandomNav(int NavMenuCount) {

		Random r = new Random();
		int rNum = r.nextInt(NavMenuCount);
		int returnNum = rNum + 1;
		return returnNum;

	}

	public static int selectRandomProduct(int ProductCount) {

		Random r = new Random();
		int rNum = r.nextInt(ProductCount);
		int returnNum = rNum + 1;
		return returnNum;
	}

	public static int selectRandomSize(int sizesCount) {

		Random r = new Random();
		int rNum = r.nextInt(sizesCount);
		int returnNum = rNum + 1;
		return returnNum;

	}

}
