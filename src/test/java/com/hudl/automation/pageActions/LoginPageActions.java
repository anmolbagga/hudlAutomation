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

	@FindBy(id = "query")
	public WebElement query;	
	

	public void launchQuikrBazarApplication() {
		logMessage("The application url is :- " + "https://www.quikr.com");
		logMessage("The test browser is :- " + "Google Chrome");
		driver.get("https://www.quikr.com");
	}

	public boolean verifyQuikrHomePageTitle(String Title) {
		logMessage("driver title is=" + driver.getTitle());
		return driver.getTitle().equals(Title);

	}

	public void searchUsedBeanBagsItemInSearchBox() {
		query.click();
		query.sendKeys("bean bags");
	}

	

	public WebElement waitForElementToload(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 80);
		return wait.until(ExpectedConditions.visibilityOf(element));

	}

	public void checkListCountForBeanBags() {
		List<WebElement> beanBagsList = driver
				.findElements(By.xpath("//*[contains(@class,'commonPad')]/div[@id='resultCont']/section/div"));
		logMessage("List Size " + beanBagsList.size());
	}

	protected void logMessage(String message) {
		System.out.println(message);
	}

//	public void waitUntilAllBeanBagsItemsAreLoadedOnThePage() {
//		doWaitUntilAllBeanBagsAreLoadedInTheList(loaderProgress, driver);
//	}

	@SuppressWarnings("unchecked")
	public void doWaitUntilAllBeanBagsAreLoadedInTheList(final WebElement loader, WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, 300);
		@SuppressWarnings("rawtypes")
		ExpectedCondition elementIsDisplayed = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver arg0) {
				try {
					scrollDownWindow();
					loader.isDisplayed();
					return false;
				} catch (NoSuchElementException e) {
					System.out.println("NO Such Element Exception Caught");
					return true;
				} catch (StaleElementReferenceException f) {
					System.out.println("Stale Element Reference Exception Caught");

					return true;
				}
			}
		};
		wait.until(elementIsDisplayed);
	}

	public void scrollDownWindow() {
		js.executeScript("window.scrollBy(0,1000)");
	}

	public void closeBrowserSession() {
		driver.quit();
	}

}