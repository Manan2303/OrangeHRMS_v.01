package TestCases;


import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjects.LoginPage;
import TestBase.baseClass_HRMS;
import Utilities.ExcelUtility;
import Utilities.dataProvider;
public class  Login_DDT_new extends baseClass_HRMS{ 

	LoginPage login;
	@Test(dataProvider = "LoginPageDataTest", dataProviderClass = dataProvider.class)
	public void testLoginPagecredentials(int rowIndex, String username, String password, String expected_status) throws IOException {

		String path = System.getProperty("user.dir") + "/test_Data/login_credentials_testdata.xlsx";
		ExcelUtility xl = new ExcelUtility(path);

		login = new LoginPage(driver);

		boolean result = false;

		try {
			login.setuser(username);
			login.setpass(password);
			login.submit();

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

			try {
				wait.until(ExpectedConditions.urlContains("dashboard"));
				result = true;
			} catch (Exception e) {
				result = false;
			}

		} catch (Exception e) {
			result = false;
		}

		int resultCol = 2; // 🔥 IMPORTANT: column C (0=user,1=pass,2=result)

		try {

			// VALID CASE
			if (expected_status.equalsIgnoreCase("valid")) {

				if (result == true) {
					xl.setCellData("Sheet1", rowIndex, resultCol, "PASS");
					xl.fillGreenColor("Sheet1", rowIndex, resultCol);
					Assert.assertTrue(true);

					login.logout(); // logout only if login success

				} else {
					xl.setCellData("Sheet1", rowIndex, resultCol, "FAIL");
					xl.fillRedColor("Sheet1", rowIndex, resultCol);
					Assert.fail();
				}
			}

			// INVALID CASE
			else if (expected_status.equalsIgnoreCase("invalid")) {

				if (result == false) {
					xl.setCellData("Sheet1", rowIndex, resultCol, "PASS");
					xl.fillGreenColor("Sheet1", rowIndex, resultCol);
					Assert.assertTrue(true);

				} else {
					xl.setCellData("Sheet1", rowIndex, resultCol, "FAIL");
					xl.fillRedColor("Sheet1", rowIndex, resultCol);
					Assert.fail();

					login.logout();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();

			xl.setCellData("Sheet1", rowIndex, resultCol, "ERROR");
			xl.fillRedColor("Sheet1", rowIndex, resultCol);
		}
	}
}
