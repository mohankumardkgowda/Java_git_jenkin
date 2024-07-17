package pack1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Url1 {
	
	WebDriver driver;
	ExtentReports extent= new ExtentReports();
	ExtentSparkReporter Spark=new ExtentSparkReporter("extent.html");

	@BeforeTest()
	public void beforetezt() {
		
		
		extent.attachReporter(Spark);
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://cvquality.acc.org/");
		driver.manage().window().maximize();
		

}
}