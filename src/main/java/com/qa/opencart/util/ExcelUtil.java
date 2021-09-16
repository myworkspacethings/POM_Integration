package com.qa.opencart.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {

	private static Workbook book;
	private static Sheet sheet;
	
	public static String TEST_DATA_SHEET_PATH= "C:\\Users\\Dell\\EclipseWorkspace\\POMSessions\\src\\main\\java\\com\\qa\\opencart\\testdata\\OpencartTestData.xlsx";
	
	public static Object[][] getTestData(String sheetName) {
		Object data[] []=null;
		try {
			FileInputStream ip=new FileInputStream(TEST_DATA_SHEET_PATH);   //connection with excel file established now
			book = WorkbookFactory.create(ip);                                                       //Workbook factory will establish connection with the patticular sheet and create will create the rreplica of that sheet in memory
																															 //and store it in refence variable
			sheet = book.getSheet(sheetName);														//Now reached at the sheet level		
			
			data=new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];			//2 dimensional object array is ready now but it is blank																							//Now read the columns and rows using 2 dimensional object
			
			for (int i = 0; i < sheet.getLastRowNum(); i++) {																					
				for (int j = 0; j < sheet.getRow(0).getLastCellNum(); j++) {
					
					data[i] [j]=sheet.getRow(i+1).getCell(j).toString();																		//now fill the data in 2 dimension i&j
					
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return data;
	}
}
