package com.automationAbhinawDemo.utilities;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.automationAbhinawDemo.test.TestBase;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class FunctionUtils extends TestBase{

	public static String uniqueString(int length) {
		String random = RandomStringUtils.randomAlphabetic(length);
		return random;		
	}

	public static String uniqueAlphaNumeric(int length) {
		String random = RandomStringUtils.randomAlphanumeric(length);
		return random;
		
	}
	
	public static int uniqueInt(int length) {
		
		String random = RandomStringUtils.randomNumeric(length);
		int res_random = Integer.parseInt(random);
		return res_random;
	}
	
	public static void highlightElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
	}
	
	public static void captureScreen(String tname) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir") + "\\screenshots\\" + tname + ".png");
		FileUtils.copyFile(source, target);
		testLogger.info("Screenshot taken fot failure in testcase "+tname);
	}
	
	public static void captureEntireScreen(String tname) throws IOException{
		 Screenshot fpscreenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
		 ImageIO.write(fpscreenshot.getImage(),"PNG",new File(System.getProperty("user.dir") + "\\screenshots\\" + tname + ".png"));       
	}
	
	public static void datePicker() {
		
		
	}
	
	
	
}
