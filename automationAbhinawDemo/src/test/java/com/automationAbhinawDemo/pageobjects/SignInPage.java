package com.automationAbhinawDemo.pageobjects;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.automationAbhinawDemo.utilities.ActionUtils;
import com.automationAbhinawDemo.utilities.FunctionUtils;
import com.automationAbhinawDemo.utilities.XL_Functions;


public class SignInPage {
	//Declare WebDriver-- this one is local driver
	WebDriver driver;
	
	//Write constructor to initiate driver and pagefactory
	public SignInPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	//write your elements here
	@FindBy(xpath="//input[@id='email']")
	public WebElement userName;
	
	@FindBy(xpath="//input[@id='passwd']")
	public WebElement password;
	
	@FindBy(xpath="//button[@id='SubmitLogin']")
	public WebElement loginBtn;
	
	@FindBy(xpath="//a[@title='Recover your forgotten password']")
	public WebElement ForgotPwdLink;
	
	@FindBy(xpath="//input[@id='email_create']")
	public WebElement CreateEmail;	
	
	@FindBy(xpath="//button[@id='SubmitCreate']")
	public WebElement SignUpLink;
	
	
	
	/*@FindBy(xpath="")
	public WebElement col1;
	
	@FindBy(xpath="")
	public WebElement col2;
	
	@FindBy(xpath="")
	public WebElement col3;*/
	
	public void setUserName(String uname) throws Exception {
		//FunctionUtils.highlightElement(userName);
		//ActionUtils.setText(userName, uname, true);
		userName.sendKeys(uname);
	}

	
	public void setPassWord(String pwd) throws IOException {
		//ActionUtils.setText(password, pwd, true);
		//password.sendKeys(XL_Functions.getCellData("pne.xlsx", "Sheet1", 1, 1));
		password.sendKeys(pwd);
	}
	
	public void clickLoginBtn() {
		ActionUtils.clickOnElement(driver,loginBtn, true);
	}
	
	public void clickForgotPwdLink() {
		ActionUtils.clickOnElement(driver,ForgotPwdLink, true);
	}
	
	public void setCreateEmail(String cemail) {
		ActionUtils.setText(driver, CreateEmail, cemail, true);
	}
	
	public void clickSignUpLink() {
		ActionUtils.clickOnElement(driver, SignUpLink, true);
	}
	
	
}
