package StepDefinition;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;

import com.hudl.automation.pageActions.LoginPageActions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Steps {

	WebDriver driver;
	LoginPageActions tstSessionUserAction;

	@Given("^Open the Firefox and launch the hudl application$")
	public void open_the_Firefox_and_launch_the_hudl_application() throws Throwable {
		System.setProperty("webdriver.gecko.driver",
				"C:\\Users\\anmol\\Downloads\\geckodriver-v0.23.0-win64\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		tstSessionUserAction = new LoginPageActions(driver);
		tstSessionUserAction.launchHudlApplication();
	}

	@When("^I Enter the wrong credentials$")
	public void i_Enter_the_wrong_credentials() throws Throwable {
		tstSessionUserAction.clickLoginButtonFromHomePage();
		tstSessionUserAction.enterCredentialsAndLogin(Username, Password);
	}

	@Then("^I am not able to login to the application$")
	public void i_am_not_able_to_login_to_the_application() throws Throwable {
		Assert.assertTrue(tstSessionUserAction.getErrorMessage().contains("help"));
		driver.quit();
	}

}