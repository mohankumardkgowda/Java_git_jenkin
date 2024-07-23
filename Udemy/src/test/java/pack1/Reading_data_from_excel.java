package pack1;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileInputStream;
import java.io.IOException;


public class Reading_data_from_excel extends Url1  {


@Test
	public void excel() throws InterruptedException {
	
	
	String excelFilePath = "C://Users//mohank//git//Java_git_jenkin//Udemy//LinkStatus.xlsx";


try (FileInputStream fileInputStream = new FileInputStream(excelFilePath);
        Workbook workbook = new XSSFWorkbook(fileInputStream)) {

       Sheet sheet = workbook.getSheetAt(0);
       int rowCount = sheet.getPhysicalNumberOfRows();

       for (int i = 0; i < rowCount; i++) {
           Row row = sheet.getRow(i);
           if (row != null) {
               Cell usernameCell = row.getCell(0);
               Cell passwordCell = row.getCell(1);

               String username = getCellValueAsString(usernameCell);
               String password = getCellValueAsString(passwordCell);

               WebElement usernameField = driver.findElement(By.id("SearchQuery"));  
               WebElement passwordField = driver.findElement(By.id("SearchQuery"));
               WebElement submit = driver.findElement(By.xpath("//input[@type='submit']"));

               usernameField.clear();  
               passwordField.clear();  

               usernameField.sendKeys(username);
               passwordField.sendKeys(password);

               
               submit.click();
               Thread.sleep(5000);
           }
       }

   } catch (IOException e) {
       e.printStackTrace();
   } finally {
       driver.quit();
   }
}

private static String getCellValueAsString(Cell cell) {
   if (cell == null) {
       return "";
   }
   switch (cell.getCellType()) {
       case STRING:
           return cell.getStringCellValue();
       case NUMERIC:
           if (DateUtil.isCellDateFormatted(cell)) {
               return cell.getDateCellValue().toString();
           } else {
               return String.valueOf(cell.getNumericCellValue());
           }
       case BOOLEAN:
           return String.valueOf(cell.getBooleanCellValue());
       case FORMULA:
           return cell.getCellFormula();
       case BLANK:
           return "";
       default:
           return "";
   }
}
}