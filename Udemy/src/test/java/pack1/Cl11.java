package pack1;

import java.util.List;

import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class Cl11 extends Url1 {
	@Test
	public void tezt() {
		ExtentTest test=extent.createTest("verify");
		test.info("Verifing");
		driver.findElement(By.xpath("//a[@href=\"/About-QII\"]")).click();
		List<WebElement> links = driver.findElements(By.tagName("a"));
		for (WebElement link : links) 
		{
			String url = link.getAttribute("href");
			System.out.println(url);
		}
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement element = driver.findElement(By.linkText("Frequently Asked Questions (FAQ)"));
		js.executeScript("arguments[0].scrollIntoView(true);", element);

		driver.findElement(By.linkText("Frequently Asked Questions (FAQ)")).click();
		test.log(Status.PASS, "Verified");
		extent.flush();
	}
}
