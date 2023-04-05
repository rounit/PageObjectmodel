package com.rs.testcases;

import org.testng.annotations.AfterSuite;

import com.rs.base.Page;

public class BaseTest 
{
	@AfterSuite
	public void tearDown()
	{
		Page.quit();
	}

}
