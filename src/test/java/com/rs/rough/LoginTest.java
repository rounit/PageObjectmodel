package com.rs.rough;

import com.rs.base.Page;
import com.rs.crm.CRMHomePage;
import com.rs.crm.account.AccountsPage;
import com.rs.crm.account.CreateAccountPage;
import com.rs.pages.HomePage;
import com.rs.pages.LoginPage;
import com.rs.pages.ZohoAppPage;



public class LoginTest {

	public static void main(String[] args) throws InterruptedException 
	{
		
		
		HomePage home = new HomePage();
		LoginPage lp = home.goToLogin();		
		ZohoAppPage zp = lp.doLogin("rounitsharmacr7@gmail.com","Rounit@1906");
		zp.gotoCRM();
		AccountsPage account = Page.menu.gotoAccounts();
		CreateAccountPage cap =   account.gotoCreateAccounts();
		cap.createAccount("Rounit");

	}

}
