package com.rs.testcases;

import java.util.Hashtable;

import org.testng.annotations.Test;

import com.rs.base.Page;
import com.rs.crm.account.AccountsPage;
import com.rs.crm.account.CreateAccountPage;
import com.rs.pages.ZohoAppPage;
import com.rs.utilities.Utilities;

public class CreateAccountTest 
{
	
	@Test(dataProviderClass=Utilities.class,dataProvider="dp")
	public void createAccountTest(Hashtable<String,String>data) throws InterruptedException
	
	{
		ZohoAppPage zp = new ZohoAppPage();
		zp.gotoCRM();
		AccountsPage account = Page.menu.gotoAccounts();
		CreateAccountPage cap =   account.gotoCreateAccounts();
		cap.createAccount(data.get("accountname"));
	}

}
