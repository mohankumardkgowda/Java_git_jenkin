package main1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Url {
	
	protected WebDriver driver;
	

	@BeforeTest()
	public void beforetezt() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://cvquality.acc.org/");
		driver.manage().window().maximize();

}
}