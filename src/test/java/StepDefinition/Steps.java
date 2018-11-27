package StepDefinition;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.hudl.automation.pageActions.LoginPageActions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Steps {

	WebDriver driver;
	LoginPageActions loginPageAction;

	@Given("^Open the Firefox and launch the hudl application$")
	public void open_the_Firefox_and_launch_the_hudl_application() throws Throwable {
		System.setProperty("webdriver.gecko.driver",
				"C:\\Users\\anmol\\Downloads\\geckodriver-v0.23.0-win64\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		loginPageAction = new LoginPageActions(driver);
		loginPageAction.launchHudlApplication();
	}

	@When("^I enter Username as \"([^\"]*)\" and Password as \"([^\"]*)\"$") 
	   public void I_enter_Username_as_and_Password_as(String Username, String Password) {
		loginPageAction.clickLoginButtonFromHomePage();
		loginPageAction.enterCredentialsAndLogin(Username, Password);
	   } 
	
	@Then("^I navigate to welcomePage$")
	public void i_navigate_to_welcomePage() throws Throwable {
	    Assert.assertTrue(loginPageAction.getPageTitle().contains("Home"));
	}

	@Then("^I am not able to login to the application$")
	public void i_am_not_able_to_login_to_the_application() throws Throwable {
		Assert.assertTrue(loginPageAction.getErrorMessage().contains("help"));
	}
	
	@And("^I close the browser$")
	public void i_close_the_browser() throws Throwable {
	    driver.quit();
	}

}