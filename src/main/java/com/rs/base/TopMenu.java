package com.rs.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.rs.crm.account.AccountsPage;

public class TopMenu 
{
	WebDriver driver ;
	public TopMenu(WebDriver driver)
	{
		this.driver=driver;
	}
	public void gotoHome()
	{
		
	}
	
	public void gotoLeads()
	{
		
	}
	
	public void gotoContacts()
	{
		
	}
	
	public void gotoDeals()
	{
		
	}
	
	public AccountsPage gotoAccounts() throws InterruptedException
	{
	    //driver.findElement(By.cssSelector("#Visible_Accounts")).click();
		Page.click("accountstab_CSS");
	    
	    return new AccountsPage();
	}
	
	public void signOut()
	{
		
	}

}
