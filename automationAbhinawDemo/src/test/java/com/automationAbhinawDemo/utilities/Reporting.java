package com.automationAbhinawDemo.utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Reporting extends TestListenerAdapter{
	
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extentReport;
	public ExtentTest logger;
	
	public void onStart(ITestContext testContext) {
		
		String time_stamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ssss").format(new Date());
		String reportName= "Abhinaw_Demo_Report "+time_stamp+".html"; //creatUniquereportName
		
		//Set Env Details
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"/test-output/"+reportName); //provide report location
		
		extentReport = new ExtentReports();
		extentReport.attachReporter(htmlReporter);
		extentReport.setSystemInfo("Host Name", "localHost");
		extentReport.setSystemInfo("Start Time", time_stamp);
		extentReport.setSystemInfo("Env", "Demo");
		extentReport.setSystemInfo("Author", "Abhinaw_Anand");
		
		
		
		//Set Configuration Details
		htmlReporter.config().setDocumentTitle("Abhinaw Demo FramworkReport");
		htmlReporter.config().setReportName(reportName);
		htmlReporter.config().setTheme(Theme.STANDARD);	
		
	}
	
	public void onTestSuccess(ITestResult testResult) {
		
		logger = extentReport.createTest(testResult.getName());
		logger.log(Status.PASS, MarkupHelper.createLabel(testResult.getName(), ExtentColor.GREEN));
	}
	
	public void onTestFailure(ITestResult testResult) {
		
		logger = extentReport.createTest(testResult.getName());
		logger.log(Status.FAIL, MarkupHelper.createLabel(testResult.getName(), ExtentColor.RED));
		logger.log(Status.FAIL, MarkupHelper.createLabel(testResult.getThrowable() + " - Test Case Failed", ExtentColor.RED));
		
	//	String path = System.getProperty("user.dir")+"\\screenshots\\"+testResult.getName().trim()+".png";
		try {
			logger.fail("Screenshot is below:" + logger.addScreenCaptureFromPath(System.getProperty("user.dir")+"\\screenshots\\"+testResult.getName()+".png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	
	public void onTestSkipped(ITestResult testResult) {
		logger = extentReport.createTest(testResult.getName());
		logger.log(Status.SKIP, MarkupHelper.createLabel(testResult.getName(), ExtentColor.GREY));
	
	}
	
	public void onFinish(ITestContext testContext)
	{
		String time_stamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ssss").format(new Date());
		extentReport.setSystemInfo("End Time", time_stamp);
		extentReport.flush();
	}

}
