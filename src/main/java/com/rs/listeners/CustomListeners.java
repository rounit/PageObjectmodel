package com.rs.listeners;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.relevantcodes.extentreports.LogStatus;
import com.rs.base.Page;


import com.rs.utilities.TestConfig;
import com.rs.utilities.Utilities;


public class CustomListeners extends Page implements ITestListener
{
	String messageBody;
	public void onTestStart(ITestResult result) 
	{
		 test = rep.startTest(result.getName().toUpperCase());
		 
	}

	public void onTestSuccess(ITestResult result) 
	{
		Page.test.log(LogStatus.PASS, result.getName().toUpperCase() +"PASS");
		rep.endTest(test);
		rep.flush();
	}

	public void onTestFailure(ITestResult result) 
	{
		System.setProperty("org.uncommons.reportng.escape-output","false");
		try
		{
		Utilities.captureScreenshot();
		}catch(IOException e)
		{
			e.printStackTrace();
		}
		
		test.log(LogStatus.FAIL, result.getName().toUpperCase()+"Failed with exception  : " + result.getThrowable());
		test.log(LogStatus.FAIL,test.addScreenCapture(Utilities.screenshotName));
		
		Reporter.log("Capturing screenshot");
		Reporter.log("<a target=\"blank\"  href="+Utilities.screenshotName+"\">Screenshot</a>");
		Reporter.log("<br>");
		Reporter.log("<a target=\"blank\"  href="+Utilities.screenshotName+"\"height=200 width=200><img src="+Utilities.screenshotName+"></img></a>");   
		rep.endTest(test);
		rep.flush();

	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		test.log(LogStatus.SKIP, result.getName().toUpperCase() + " Skipped the test as the Run Mode is NO");
		rep.endTest(test);
		rep.flush();
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	public void onFinish(ITestContext context) 
	{
		//MonitoringMail mail = new MonitoringMail();
		
		try {
			messageBody = "http://"+(InetAddress.getLocalHost().getHostAddress())+":8080/job/DataDrivenLiveProject/Extent_20Report/";
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		try {
//			//mail.sendMail(TestConfig.server, TestConfig.from, TestConfig.to, TestConfig.subject, messageBody);
//		} catch (AddressException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (MessagingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	}

}
