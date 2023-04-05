package com.rs.crm.account;

import org.openqa.selenium.By;

import com.rs.base.Page;

public class CreateAccountPage extends Page
{
	public void createAccount(String accountname) throws InterruptedException
	{
		Thread.sleep(2000);
		//driver.findElement(By.xpath("//input[@id='Crm_Accounts_ACCOUNTNAME_LInput']")).sendKeys(accountname);
		type("accountname",accountname);

	}

}
