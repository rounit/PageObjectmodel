package com.rs.utilities;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Hashtable;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.DataProvider;

import com.rs.base.Page;



public class Utilities extends Page 
{
	public static String screenshotPath;
	public static String screenshotName;
	public static void captureScreenshot() throws IOException
	{
		File screFile =  ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		Date d = new Date();
		screenshotName = d.toString().replace(":", "_").replace(" ", "_")+".jpg";
		FileUtils.copyFile(screFile, new File(System.getProperty("user.dir")+"\\target\\surefire-reports\\html\\"+screenshotName));
	}
	
	@DataProvider(name="dp")
	public Object[][] getData(Method m)
	{
		
		String sheetName = m.getName();
		int rows = excel.getRowCount(sheetName);
		int cols = excel.getColumnCount(sheetName);
		
		Object[][]  data = new Object[rows - 1] [1];
		
	     Hashtable<String,String> table = null;
		
		for(int rowNum=2;rowNum<=rows;rowNum++)
		{
			table = new Hashtable<String,String>();
			for(int colNum=0;colNum<cols;colNum++)
			{
				table.put(excel.getCellData(sheetName, colNum, 1), excel.getCellData(sheetName, colNum, rowNum));
				data[rowNum-2][0] = table;
			}
		}
		
		return data;
	}
	
	public static boolean isTestRunnable(String TestName, ExcelReader excel)
	{
		String sheetName = "test_suite" ;
		int rows = excel.getRowCount(sheetName);
		for(int rNum =2 ; rNum<=rows ;rNum++)
		{
			String testCase =  excel.getCellData(sheetName, "TCID", rNum);
			
			if(testCase.equalsIgnoreCase(TestName))
			{
				String runmode = excel.getCellData(sheetName, "Runmode", rNum);
				
				if(runmode.equalsIgnoreCase("Y"))
					return true;
				else
					return false;
			}
			
			
		}
		
		return false;
	}

}
