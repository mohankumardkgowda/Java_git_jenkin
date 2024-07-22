package pack1;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;

public class Reading_data_from_excel {


@Test
	public void excel() {
	        String excelFilePath = "C://Users//mohank//git//Java_git_jenkin//Udemy//LinkStatus.xlsx";
	        FileInputStream fileInputStream = null;
	        Workbook workbook = null;

	        try {
	            fileInputStream = new FileInputStream(excelFilePath);
	            workbook = new XSSFWorkbook(fileInputStream);
	            Sheet sheet = workbook.getSheetAt(0);

	            for (Row row : sheet) {
	                for (Cell cell : row) {
	                    switch (cell.getCellType()) {
	                        case STRING:
	                            System.out.print(cell.getStringCellValue() + "\t");
	                            break;
	                        case NUMERIC:
	                            System.out.print(cell.getNumericCellValue() + "\t");
	                            break;
	                        case BOOLEAN:
	                            System.out.print(cell.getBooleanCellValue() + "\t");
	                            break;
	                        default:
	                            System.out.print("Unknown value\t");
	                            break;
	                    }
	                }
	                System.out.println();
	                
	                
	                
	        
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            if (workbook != null) {
	                try {
	                    workbook.close();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	            if (fileInputStream != null) {
	                try {
	                    fileInputStream.close();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
	    }
	
}
