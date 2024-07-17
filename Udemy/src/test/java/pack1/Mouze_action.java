package pack1;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class Mouze_action extends Url1{

	@Test
	public void mouze() {
		WebElement ncdr=driver.findElement(By.xpath("//a[@href=\"/NCDR-Home\"]"));
		WebElement ncd=driver.findElement(By.xpath("//a[@href=\"/accreditation\"]"));
		WebElement ncr=driver.findElement(By.xpath("//a[@href=\"/initiatives\"]"));
		WebElement ndr=driver.findElement(By.xpath("//a[@href=\"/clinical-toolkits\"]"));
		WebElement nr=driver.findElement(By.xpath("//a[@href=\"#\" ]"));
		Actions action=new Actions(driver);
		action.moveToElement(ncdr).perform();
		action.moveToElement(ncd).perform();
		action.moveToElement(ncr).perform();
		action.contextClick(ndr).perform();
		action.doubleClick(nr).perform();
		driver.findElement(By.xpath("//*[@id=\"SearchQuery\"]")).sendKeys("abc");
		driver.findElement(By.xpath("//*[@id=\"SearchQuery\"]")).sendKeys(Keys.CONTROL+"A");
		driver.findElement(By.xpath("//*[@id=\"SearchQuery\"]")).sendKeys(Keys.CONTROL+"X");
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement targetElement = driver.findElement(By.xpath("//*[@id=\"MainContent_C039_Col00\"]/div/div[2]/a"));
        js.executeScript("arguments[0].scrollIntoView(true);", targetElement);
        
		Set<String> aaa= driver.getWindowHandles();
		System.out.println(aaa);
		driver.switchTo().newWindow(WindowType.TAB);
		driver.get("https://cvquality.acc.org/");
		
        WebElement targetElement1 = driver.findElement(By.xpath("//*[@id=\"MainContent_C039_Col00\"]/div/div[2]/a"));
        js.executeScript("arguments[0].scrollIntoView(true);", targetElement1);
        // Scroll horizontally until the element is in view
        //  js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'nearest', inline: 'start'});", targetElement);

        //upload file
        //driver.findElement(By.xpath("//*[@id=\"Search"]")).sendKeys("file location and name");
        
        //upload multiple file
        //String file1="file 1 location";
        //String file2="file 2 location";
        //driver.findElement(By.xpath("//*[@id=\"Search"]")).sendKeys(file1+"\n"+file2);
	}
}
