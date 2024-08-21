package AutoTestPLT.TestComponents;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;


public class BaseTest {

	public WebDriver driver;

	@BeforeSuite
	public WebDriver invokeBrowser() throws IOException {

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+
				"\\src\\AutoTestPLT\\resources\\GlobalData.properties");
		prop.load(fis);
		String selectedBrowser = prop.getProperty("Browser");
		String selectedUrl = prop.getProperty("Url");

		if (selectedBrowser.equals("Chrome")) {

			System.setProperty("webdriver.chrome.driver",
					"C:\\Users\\user\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.get(selectedUrl);

		}
		if (selectedBrowser.equals("Firefox")) {

			System.setProperty("webdriver.gecko.driver",
					"C:\\Users\\user\\Downloads\\geckodriver-v0.34.0-win64\\geckodriver.exe");
			driver = new FirefoxDriver();
			driver.get(selectedUrl);

		}
		if (selectedBrowser.equals("Edge")) {

			System.setProperty("webdriver.edge.driver",
					"C:\\Users\\user\\Downloads\\edgedriver_win64\\msedgedriver.exe");
			driver = new EdgeDriver();
			driver.get(selectedUrl);
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.manage().window().maximize();
		return driver;

	}
	
	@AfterSuite
	public WebDriver closeWindow() {
		driver.close();
		System.out.println("Browser has been closed");
		return driver;
	}
	

}
