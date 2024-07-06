package com.qa.opencart.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {
   
	private static final String TEST_DATA="./src/test/resources/testdata/Book.xlsx";
	private static Workbook book;
	private static Sheet sheet;
	
	
	public static void getTestData(String shetName)
	{
		try {
			FileInputStream fis = new FileInputStream(TEST_DATA);
			try {
				book = WorkbookFactory.create(fis);
				sheet =book.getSheet(shetName);
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
			} catch (InvalidFormatException e) {
		
				e.printStackTrace();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			
			
			
			
			
			
			
			
			
			
			
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
