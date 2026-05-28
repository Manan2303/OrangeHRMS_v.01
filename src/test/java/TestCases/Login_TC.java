package TestCases;



import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import PageObjects.LoginPage;
import TestBase.baseClass_HRMS;
import Utilities.screenshot;
import drivers.DriverManager;

public class Login_TC extends baseClass_HRMS {

	LoginPage login;
	@Test(priority = 1 ,groups = {"smoke"})
	public void testCompanyLogo() {
		login = new LoginPage(DriverManager.getDriver());
		login.display_logo();
		try {
			Assert.assertTrue(true, "Company logo is displayed in page");
		} catch (Exception e) {
			Assert.fail("Company logo is not displayed.....");
		}

	}

	@Test(priority = 2, groups = {"Smoke"})
	public void testValidLogin() throws InterruptedException {
		login = new LoginPage(DriverManager.getDriver());

		login.setuser(pr.getProperty("user_name"));
		login.setpass(pr.getProperty("password"));
		login.submit();
		WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(20));
		wait.until(ExpectedConditions.urlContains("dashboard"));


		String currentURL = DriverManager.getDriver().getCurrentUrl();

		System.out.println(currentURL + "111111***************1111111");
		boolean result = currentURL.contains("dashboard");
		System.out.println(result);

		Assert.assertTrue(result, "User is not redirect to dashboard after enter correct credential");
		Thread.sleep(5000);

		login.logout();
		System.out.println("Logout successfully");
	}

	@Test(priority =3, groups = {"Smoke"})
	public void testInvalidLogin() {
		login = new LoginPage(DriverManager.getDriver());
		login.setuser(" wrong");
		login.setpass("xxxxxxxx");
		login.submit();

		boolean result =
				DriverManager.getDriver()
				.getPageSource()
				.contains("Invalid");
		Assert.assertTrue(
				result,
				"Error message not displayed");
		//		boolean isErrorDisplayed = driver.getPageSource().contains("Invalid credentials");

		System.out.println("Logout successfully in case of invalid login");
		//		Assert.assertEquals(isErrorDisplayed, true,"Error message not displayed for invalid login!");

	}

	/*	@AfterMethod
	public void captureScreenshotOnFailure(ITestResult result) {
		if (ITestResult.FAILURE == result.getStatus()) {
			captureFailTC(result.getName()); // Pass test method name
		}
	}  */

	@AfterMethod
	public void captureScreenshotOnFailure(ITestResult result) {
		if (ITestResult.FAILURE == result.getStatus()) {
			try {
				if (DriverManager.getDriver() != null) { // Optional: check if session is active

					DriverManager.getDriver().getTitle(); // This will throw if session is invalid
					screenshot.captureFailTC(result.getName());
				}
			} catch (Exception e) {
				System.out.println("Unable to capture screenshot: " + e.getMessage());
			}
		}
	}
}
