package com.automationAbhinawDemo.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.automationAbhinawDemo.pageobjects.SignInPage;
import com.automationAbhinawDemo.utilities.FunctionUtils;


//import junit.framework.Assert;

public class TC_001_SuccessfulLogin extends TestBase{
	
	
	@Test()
	public void login() throws Exception {
		SignInPage sip = new SignInPage(driver);
		System.out.println(sip);
		sip.setUserName(userName);
		testLogger.info("Written UserName Successfully:: "+userName);
		sip.setPassWord(password);
		testLogger.info("Written Password Successfully");
		sip.clickLoginBtn();
		testLogger.info("clicking on login Btn");
		
		String title = driver.getTitle();
		testLogger.info(title);

		if(title.equals(pageT))
			{
				Assert.assertTrue(true);
				testLogger.info("About to capture SS");
				
				
			}
			else {
				FunctionUtils.captureScreen("login");
				Assert.assertTrue(false);
				
			}
		
	}
	
	@Test(groups= {"checkinTests"})
	public void PassTest() {
		Assert.assertTrue(true);
	}
	public void testOverload(long b, long a) {
		System.out.println("Test2");
			
		}
	public void testOverload(int a, int b) {
		System.out.println("Test1");
		
	}

	
	@Test(dependsOnMethods="login")
	public void skipTest() {
		Assert.assertTrue(true);
	}
	 
}
