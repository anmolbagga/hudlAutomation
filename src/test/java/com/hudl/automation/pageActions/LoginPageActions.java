package com.hudl.automation.pageActions;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

public class LoginPageActions {

	public WebDriver driver;
	JavascriptExecutor js;

	public LoginPageActions(WebDriver driver) {
		this.driver = driver;
		// This initElements method will create all WebElements
		PageFactory.initElements(this.driver, this);
		js = (JavascriptExecutor) driver;
	}

	// ****************************************************************************************************//
	/*
	 * WebElement are identified by @FindBy Annotation
	 * 
	 * @FindBy can accept tagName, partialLinkText, name, linkText, id, css,
	 * className, xpath as attributes.
	 */

	@FindBy(xpath = "//a[@class='btn__light--ghost login']")
	public WebElement loginButtonInHomePage;

	@FindBy(xpath = "//input[@id='email']")
	public WebElement emailInputField;

	@FindBy(xpath = "//input[@id='password']")
	public WebElement passwordInputField;

	@FindBy(xpath = "//button[@id='logIn']")
	public WebElement loginButtonToLogin;

	@FindBy(xpath = "//div[@class='login-error-container']//p")
	public WebElement errorMessage;

	@FindBy(xpath = "//div[@class='login-error-container']//a[@class='need-help']")
	public WebElement needHelpLink;

	@FindBy(xpath = "//div[@class='login-error-container']/*[1]")
	public WebElement questionMarkIcon;

	public void launchHudlApplication() {
		logMessage("The application url is :- " + "https://www.hudl.com/");
		logMessage("The test browser is :- " + "Google Chrome");
		driver.get("https://www.hudl.com/");
	}

	public String getHudlLoginPageTitle() {
		logMessage("driver title is=" + driver.getTitle());
		return driver.getTitle();
	}

	public void clickLoginButtonFromHomePage() {
		loginButtonInHomePage.click();
	}

	public void enterUserName(String Username) {
		emailInputField.sendKeys(Username);
	}

	public void enterPassword(String Password) {
		passwordInputField.sendKeys(Password);
	}

	public void clickLoginButton() {
		loginButtonToLogin.click();
	}
	
	public void enterCredentialsAndLogin(String Username, String Password) {
		enterUserName(Username);
		enterPassword(Password);
		clickLoginButton();
	}
	
	public String getErrorMessage() {
		waitForElementToload(needHelpLink);
		System.out.println("error message is "+needHelpLink.getText());
		return needHelpLink.getText();
	}
	
	public WebElement waitForElementToload(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		return wait.until(ExpectedConditions.visibilityOf(element));
	}

	protected void logMessage(String message) {
		Reporter.log(message);
	}

	public void scrollDownWindow() {
		js.executeScript("window.scrollBy(0,1000)");
	}

	public void closeBrowserSession() {
		driver.quit();
	}

}