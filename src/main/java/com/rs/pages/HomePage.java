package com.rs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.rs.base.Page;

public class HomePage extends Page
{
	
	
	
	
	public void goToSupport()
	{
		driver.findElement(By.cssSelector("a[class='zh-support']")).click();
	}
	
	public void goToSignUp()
	{
		driver.findElement(By.cssSelector("a[class='zh-signup']")).click();
	}
	
	public LoginPage goToLogin()
	{
         click("loginlink_CSS");
         
         return new LoginPage();
	}
	
	public void gotoZohoEdu()
	{
		driver.findElement(By.cssSelector("a[class='zh-contact']")).click();
	}
	
	public void goToLearnMore()
	{
		
		
	}
	
	public void validateFooterLinks()
	{
		
	}

}
