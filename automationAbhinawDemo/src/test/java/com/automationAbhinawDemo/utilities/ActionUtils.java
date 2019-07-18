package com.automationAbhinawDemo.utilities;

import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.automationAbhinawDemo.test.TestBase;

import junit.framework.Assert;

public class ActionUtils extends TestBase {
	
	public static void clickOnElement(WebDriver driver, WebElement element, boolean waitForElement) {
		
		Actions act = new Actions(driver);
		if(waitForElement) {
			act.moveToElement(element).click(element).build().perform();
		}
		else 
			act.moveToElement(element).click(element).build().perform();
	}
	
	public static void clickByJsMethod(WebDriver driver, WebElement element, boolean waitForElement) {
		
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		if(waitForElement) {
			executor.executeScript("arguments[0].click();", waitFor(driver,element,5,"clickable"));
		}
		else
			executor.executeScript("arguments[0].click();", element);
	}
	
	public static void selectFrom(WebDriver driver, WebElement element, String selectMethod, String value_index) {
		
		Select select = new Select(waitFor(driver, element,5,"visibilityOf"));
		if(selectMethod.toLowerCase().contentEquals("text")) {
			select.selectByVisibleText(value_index);
		}
		
		if(selectMethod.toLowerCase().contentEquals("value"))
		{
			select.selectByValue(value_index);
		}
		
		if(selectMethod.toLowerCase().contentEquals("value")) {
			int index = Integer.parseInt(value_index);
			select.selectByIndex(index);
		}
	}
	
	public static void deselectFrom(WebDriver driver, WebElement element, String selectMethod, String value_index) {
		
		Select select = new Select(waitFor(driver,element,5,"visibilityOf"));
		if(selectMethod.toLowerCase().contentEquals("text")) {
			select.deselectByVisibleText(value_index);
		}
		
		if(selectMethod.toLowerCase().contentEquals("value"))
		{
			select.deselectByValue(value_index);
		}
		
		if(selectMethod.toLowerCase().contentEquals("value")) {
			int index = Integer.parseInt(value_index);
			select.deselectByIndex(index);
		}
		
		
	}
	
	public static WebElement waitFor(WebDriver driver, WebElement element, long timeOutInSeconds, String expectedCondition ){
		WebElement returnelment = null;
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		if(expectedCondition.toLowerCase().contains("visibilityOf")) {
			returnelment= wait.until(ExpectedConditions.visibilityOf(element));
			return returnelment;
		}
		
		if(expectedCondition.toLowerCase().contains("clickable")) {
			returnelment = wait.until(ExpectedConditions.elementToBeClickable(element));
		}
		return returnelment;
	}
	
	
	
	public static void setText(WebDriver driver, WebElement element, String txt, boolean waitForElement) {
		
		Actions act = new Actions(driver);
		if(waitForElement) {
			waitFor(driver,element,5,"visibilityOf").clear();
			act.moveToElement(element).sendKeys(txt).build().perform();
		}
		else
			element.clear();
			act.moveToElement(element).sendKeys(txt).build().perform();
		
	}
	
	public static void handleAlert() {
		
		
	}
	
	public static void assertText(String expected, String actual, String compartor, boolean CaseSensitve, String casename) {
		if(compartor.equalsIgnoreCase("Equal")) {
		if(CaseSensitve) {
			if(actual.equals(expected)) {
				Assert.assertEquals(expected, actual);
			} else
				try {
					FunctionUtils.captureScreen(casename);
					Assert.assertEquals(expected, actual);
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		
		else
			if(actual.equalsIgnoreCase(expected))
			 {
				Assert.assertEquals(expected, actual);
			} else
				try {
					FunctionUtils.captureScreen(casename);
					Assert.assertEquals(expected, actual);
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		
		if(compartor.equalsIgnoreCase("Contains")) {
			if(CaseSensitve) {
				if(expected.contains(actual)) {
					Assert.assertTrue(expected+" contains "+actual, true);
				} else
					try {
						FunctionUtils.captureScreen(casename);
						Assert.assertTrue(expected+" doesn't contains "+actual, false);
					} catch (IOException e) {
						e.printStackTrace();
					}
			}
			
			else
				if(expected.toLowerCase().contains(actual.toLowerCase()))
				 {
					Assert.assertTrue("", true);
				} else
					try {
						FunctionUtils.captureScreen(casename);
						Assert.assertTrue("", false);
					} catch (IOException e) {
						e.printStackTrace();
					}
			}
	
		
	}
	
	public static void handleFrame() {
		
		
	}
	
	public static void handleWindows() {
		
	}
	
	public static void doubleClick(WebDriver driver, WebElement element, boolean waitForElement) {
		Actions act = new Actions(driver);
		if(waitForElement) {
			act.moveToElement(waitFor(driver,element,5,"visibilityOf")).doubleClick().build().perform();
		}
		else
			act.moveToElement(element).doubleClick().build().perform();
			
	}
	
	public static void tableHandling() {
		
		
	}
	
	
	
}
