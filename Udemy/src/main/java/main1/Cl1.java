package main1;

import java.io.IOException;
import java.util.List;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class Cl1 extends Url{
	
@Test
public void tezt() {
	//List<WebElement> link=driver.findElements(By.tagName("a"));
//	System.out.println(link.size());
//	System.out.println(link);
	
	
	 List<WebElement> links = driver.findElements(By.tagName("a"));

     // Create an HttpClient instance
     try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
         for (WebElement link : links) {
             String url = link.getAttribute("href");
             if (url != null && !url.isEmpty()) {
                 verifyLink(url, httpClient);
             }
         }
     } catch (IOException e) {
         e.printStackTrace();
     }

     // Close the WebDriver
     driver.quit();
 }




 private static void verifyLink(String url, CloseableHttpClient httpClient) {
     try {
         HttpGet request = new HttpGet(url);
         try (@SuppressWarnings("deprecation")
		CloseableHttpResponse response = httpClient.execute(request)) {
             int statusCode = response.getCode();
             if (statusCode >= 200 && statusCode <= 299) {
                 System.out.println("Valid link: " + url + " - " + statusCode); 
             } else {
                 System.out.println("Broken link: " + url + " - " + statusCode);
             }
         }
     } catch (IOException e) {
         System.out.println("Error checking link: " + url);
         e.printStackTrace();
     }
 }
}

