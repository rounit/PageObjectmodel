package com.rs.pages;

import org.openqa.selenium.By;

import com.rs.base.Page;
import com.rs.crm.CRMHomePage;

public class ZohoAppPage extends Page
{
    
    
    public CRMHomePage gotoCRM()
    {
    	//driver.findElement(By.xpath("//*[@id=\"zl-myapps\"]/div[1]/div[7]/div/a/span")).click();
    	click("crmlink");
    	return new CRMHomePage();
    }
    
    public void gotoCliq()
    {
    	driver.findElement(By.xpath("//*[@id=\"zl-myapps\"]/div[1]/div[5]/div/a/span")).click();
    }
    
    public void gotoProjects()
    {
    	driver.findElement(By.xpath("//*[@id=\"zl-myapps\"]/div[1]/div[11]/div/a")).click();
    }


}
