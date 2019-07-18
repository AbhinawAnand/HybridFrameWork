package com.automationAbhinawDemo.test;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.automationAbhinawDemo.utilities.ReadPropertiesFile;

public class TestBase {
	ReadPropertiesFile rf = new ReadPropertiesFile();
	public String url = rf.getPropertyFileValue("url");
	public String userName = rf.getPropertyFileValue("userName");
	public String password =  rf.getPropertyFileValue("password");
	public String pageT = rf.getPropertyFileValue("pageT");
	public static  WebDriver driver;
	public static Logger testLogger;
	
	@BeforeSuite()
	public void bs() {
		testLogger= testLogger.getLogger("abhinaw-automation-demo");
		PropertyConfigurator.configureAndWatch("log4j.properties");
		System.out.println("This is before suite");
		testLogger.info("This is before suite");
	}
	
	@AfterSuite()
	public void as() {
		
		System.out.println("This is after suite");
		testLogger.info("This is after suite");
	}
	
	@BeforeTest()
	public void bt()
	{
		
		System.out.println("This is beofre test");
		testLogger.info("This is before test");
	}
	
	@AfterTest()
	public void at() {
		System.out.println("This is After Test");
		testLogger.info("This is after test");
	}
	
	
	@BeforeGroups()
	public void bg() {
		System.out.println("This is before grp");
		testLogger.info("This is before grp");
	}
	
	@AfterGroups()
	public void ag() {
		System.out.println("This is after ag");
		testLogger.info("This is after grp");
	}
	
	//@Parameters("browser")
	@BeforeClass
	public void setUp(String br) {
		System.out.println("This is before class");
	/*	testLogger= testLogger.getLogger("abhinaw-automation-demo");
		PropertyConfigurator.configureAndWatch("log4j.properties");*/
		
		if(br.equalsIgnoreCase("Chrome")) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+rf.getPropertyFileValue("chromedriverpath"));
			ChromeOptions options = new ChromeOptions();
	        options.setExperimentalOption("useAutomationExtension", false);
			
			options.addArguments("--headless");
	        options.addArguments("--disable-extensions"); // disabling extensions
	        options.addArguments("--disable-gpu"); // applicable to windows os only
	        options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
	        options.addArguments("--no-sandbox");
	 
			driver = new ChromeDriver();
		}
		
	else if (br.equalsIgnoreCase("Firefox")) {
			
			System.setProperty("webdriver.firefox.driver", System.getProperty("user.dir")+rf.getPropertyFileValue("firefoxdriverpath"));
			driver = new FirefoxDriver();
		}
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.get(url);
		testLogger.info("Launched Url Successfully:: "+url);
		driver.manage().window().maximize();
		
	}

	
	@AfterClass
	public void tearDown() {
		driver.close();
		driver.quit();
	}
	

	
}
