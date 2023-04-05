package com.rs.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.rs.utilities.ExcelReader;
import com.rs.utilities.ExtentManager;

import com.rs.utilities.Utilities;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Page 
{
	public static WebDriver driver;
	public static Properties Config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream fis;
	public static ExcelReader excel = new ExcelReader(System.getProperty("user.dir")+"\\src\\test\\resources\\com\\rs\\excel\\testdata.xlsx");
	public static WebDriverWait wait;
	public ExtentReports rep = ExtentManager.getInstance();
	public static ExtentTest test;
	public static String browser;
	public static TopMenu menu;  
	
	
	public Page()
	{
		if(driver==null)
		{
			try {
				fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\com\\rs\\properties\\Config.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		try {
			Config.load(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		 try {
			fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\com\\rs\\properties\\OR.properties");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 try {
			OR.load(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		 
		 if(System.getenv("browser")!=null && !System.getenv("browser").isEmpty()){
				
				browser = System.getenv("browser");
			}else{
				
				browser = Config.getProperty("browser");
				
			}
			
			Config.setProperty("browser", browser);
			
		 
		 if(Config.getProperty("browser").equals("firefox"))
		 {
			 driver = new FirefoxDriver();
		 }
		 
		 else if(Config.getProperty("browser").equals("chrome"))
		 {
			/* System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\src\\test\\resources\\executables\\chromedriver.exe");
			 driver = new ChromeDriver();*/
			 ChromeOptions options = new ChromeOptions();
			 options.addArguments("--remote-allow-origins=*");
			 WebDriverManager.chromedriver().setup();
			 driver = new ChromeDriver(options);
		 }
	
			
		  Map<String,Object> prefs = new HashMap<String,Object>();
		  prefs.put("profile.default_content_setting_values.notifications", 2);
		  prefs.put("credentials_enable_service", false);
		  prefs.put("profile.password_manager_enabled", false);
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		options.setExperimentalOption("prefs", prefs);
		options.addArguments("--disable-extensions");
		options.addArguments("--disable-infobars");
		
		driver.get(Config.getProperty("testsiteurl"));
		 driver.manage().window().maximize();
		 driver.manage().timeouts().implicitlyWait(Integer.parseInt(Config.getProperty("implicit.wait")),TimeUnit.SECONDS);
		 wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		menu = new TopMenu(driver);
		}
		
		
	}
	
	public static void quit()
	{
		driver.quit();
	}
	
	
	public static void click(String locator)
	{
		driver.findElement(By.cssSelector(OR.getProperty(locator))).click();
		
		test.log(LogStatus.INFO, "Clicking on : " + locator);
		
		
	}
	
	public static void type(String locator, String value)
	{
		driver.findElement(By.cssSelector(OR.getProperty(locator))).sendKeys(value);
		test.log(LogStatus.INFO, "Typing in : : " + locator + " Enetred value as " + value);
	}
	
	static WebElement dropdown;
	public static void select(String locator,String value)
	{
		dropdown = driver.findElement(By.cssSelector(OR.getProperty(locator)));
		
		
		Select sel = new Select(dropdown);
		sel.selectByVisibleText(value);
		
		test.log(LogStatus.INFO, "Selecting from dropdown : +  " + locator + "valuse as " + value);
	}
	
	
	
	public boolean isElementPresent(By by)
	{
		try
		{
			driver.findElement(by);
			return true;
		}
		catch(NoSuchElementException e)
		{
			return false;
		}
	}
	
	
	public static void verifyEquals(String expected , String actual) throws IOException
	{
		try
		{
			Assert.assertEquals(actual, expected);
		}
		catch(Throwable t)
		{
			Utilities.captureScreenshot();
			Reporter.log("<br>" + " Verification failure : " + t.getMessage() + "<br>");
			//Report NG
			Reporter.log("<a target=\"blank\"  href="+Utilities.screenshotName+"\"height=200 width=200><img src="+Utilities.screenshotName+"></img></a>");
			Reporter.log("<br>");
			Reporter.log("<br>");
			//Extent
			test.log(LogStatus.FAIL, "Verification failed with exception  : " + t.getMessage());
			test.log(LogStatus.FAIL,test.addScreenCapture(Utilities.screenshotName));
		}

}
}