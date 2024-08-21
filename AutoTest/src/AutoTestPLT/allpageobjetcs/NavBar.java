package AutoTestPLT.allpageobjetcs;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class NavBar {

	WebDriver driver;
	
	public NavBar(WebDriver driver) {
		
		this.driver = driver;
		
	}
	
	public void navClick()
	{
		WebElement navBar = driver.findElement(By.cssSelector("ul[data-orientation='horizontal']"));
		int navMenuCount = navBar.findElements(By.xpath("//nav/div/ul/li/a")).size();
		int randomNav = selectRandomNav(navMenuCount);
		driver.findElement(By.xpath("//nav/div/ul/li[" + randomNav + "]")).click();
		
	}
	
	public static int selectRandomNav(int NavMenuCount) {

		Random r = new Random();
		int rNum = r.nextInt(NavMenuCount);
		int returnNum = rNum + 1;
		return returnNum;

	}
}
