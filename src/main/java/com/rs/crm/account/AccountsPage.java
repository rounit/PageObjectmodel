package com.rs.crm.account;

import org.openqa.selenium.By;

import com.rs.base.Page;

public class AccountsPage extends Page
{
	public CreateAccountPage gotoCreateAccounts()
	{
		//driver.findElement(By.xpath("//*[@id=\"table_row_1\"]/lyte-td[3]/span[1]/link-to/button")).click();
		click("createaccountbtn");
		return new CreateAccountPage();
	}

}
