package TestCases;


import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import PageObjects.LoginPage;
import PageObjects.addJob;
import TestBase.baseClass_HRMS;
import Utilities.screenshot;
import drivers.DriverManager;

public class jobTitle_TC extends baseClass_HRMS{
	LoginPage lp;
	addJob job;

	@Test(groups  = {"sanity","smoke"})
	public void addJob_test(){
		lp = new LoginPage(DriverManager.getDriver());
		lp.display_logo();
		try {
			Assert.assertTrue(true, "Company logo is  not displayed in page");
		} catch (Exception e) {
			Assert.fail("Company logo is not displayed.....");
		}

		lp.setuser("Admin");
		lp.setpass("admin123");
		lp.submit();
		WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(20));
		wait.until(ExpectedConditions.urlContains("dashboard"));

		System.out.println(DriverManager.getDriver().getCurrentUrl() + " *********************************** ");
		boolean result = DriverManager.getDriver().getCurrentUrl().contains("dashboard");
		System.out.println(result);

		Assert.assertTrue(result, "User is not redirect to dashboard after enter correct credential");

		// access add job page 
		job = new addJob(DriverManager.getDriver());

		job.adminMenu();
		System.out.println("Add job page open successfully...... heeeheeeeee!!!!");
		job.verifyPage();
		job.inputDetails("Automation Test Engineer L34", " nsgdhsaghdj jdsafjdfas bfsdghfsaghdfhgasfd hsafdghsafghdfsahgfdhgsafdhgsa bjfsdgsafhgdfasghdfasgh dsfadgsafdhgsafgdjfas bjhafsdfsaghfdasd asgjasfgfahg", "For testing Purpose check job ");
		job.submit();
	}
	
	@AfterMethod

	public void captureScreenshotOnFailure(ITestResult result) {
		logger.info("capture screenshot for test fail....");
		logger.error("Test Failed");
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