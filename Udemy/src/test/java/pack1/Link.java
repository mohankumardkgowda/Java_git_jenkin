package pack1;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class Link extends Url1 {
    private static final int TIMEOUT = 3000;
    private static final int THREAD_POOL_SIZE = 10;
    private static final String FILE_PATH = "LinkStatus.xlsx";

    @Test
    public void link() {
        List<WebElement> links = driver.findElements(By.tagName("a"));

        ExecutorService executor = Executors.newFixedThreadPool(THREAD_POOL_SIZE);

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Link Status");
            AtomicInteger rowNum = new AtomicInteger(0);

            // Create header row
            Row headerRow = sheet.createRow(rowNum.getAndIncrement());
            headerRow.createCell(0).setCellValue("Link");
            headerRow.createCell(1).setCellValue("Status Code");
            headerRow.createCell(2).setCellValue("Status");

            for (WebElement link : links) {
                String url = link.getAttribute("href");
                if (url != null && !url.isEmpty()) {
                    executor.execute(() -> {
                        int statusCode = verifyLink(url);
                        String status = (statusCode >= 400) ? "Broken" : "Valid";
                        synchronized (sheet) {
                            Row row = sheet.createRow(rowNum.getAndIncrement());
                            row.createCell(0).setCellValue(url);
                            row.createCell(1).setCellValue(statusCode);
                            row.createCell(2).setCellValue(status);
                        }
                    });
                }
            }

            executor.shutdown();
            executor.awaitTermination(1, TimeUnit.HOURS);

            try (FileOutputStream fileOut = new FileOutputStream(FILE_PATH)) {
                workbook.write(fileOut);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        driver.quit();
    }

    public static int verifyLink(String linkUrl) {
        try {
            URL url = new URL(linkUrl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setConnectTimeout(TIMEOUT);
            httpURLConnection.connect();

            return httpURLConnection.getResponseCode();
        } catch (IOException e) {
            System.out.println(linkUrl + " is a broken link. Exception: " + e.getMessage());
            return -1; // return -1 to indicate an exception occurred
        }
    }
}
