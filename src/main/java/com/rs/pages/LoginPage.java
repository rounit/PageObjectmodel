package com.rs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.rs.base.Page;

public class LoginPage extends Page
{
	
	
    public ZohoAppPage doLogin(String username,String password)
    {
    	type("email_CSS",username);
    	click("NextBtn");
    	type("passwordXPATH",password);
    	click("NxtBtn");
    	
    	return new ZohoAppPage();
    }
    
    public void gotoSales()
    {
    	
    }
    
    public void gotoSalesMarketing()
    {
    	
    }
}
