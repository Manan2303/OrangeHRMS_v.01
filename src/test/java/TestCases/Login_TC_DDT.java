package TestCases;



import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjects.LoginPage;
import TestBase.baseClass_HRMS;
import Utilities.ExcelUtility;
import Utilities.dataProvider;

public class Login_TC_DDT extends baseClass_HRMS {

    LoginPage login;
// apply color code in excel
    @Test(dataProvider = "LoginDataTest", dataProviderClass = dataProvider.class)
    public void testlogincredentials(int rowIndex, String username, String password, String expected_status) {

        String path = System.getProperty("user.dir") + "/test_Data/login_credentials_testdata.xlsx";
        ExcelUtility xl = new ExcelUtility(path);

        login = new LoginPage(driver);

        boolean result = false;

        try {
            login.setuser(username);
            login.setpass(password);
            login.submit();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.urlContains("dashboard"));

            result = driver.getCurrentUrl().contains("dashboard");
            System.out.println(result + " /////////////////////////////////////*****************************");

        } catch (Exception e) {
            result = false;
        }
        System.out.println(result + " /////////////////////////////////////*****************************");
        int resultCol = 3; // Result column (0-based: 0=user,1=pass,2=status,3=result)

        try {

            // VALID CASE
            if (expected_status.equalsIgnoreCase("valid")) {
            	
            	System.out.println("enter the valid case if condition");

                if (result) {
                    Assert.assertTrue(true);
                    xl.setCellData("Sheet1", rowIndex, resultCol, "PASS");
                    xl.fillGreenColor("Sheet1", rowIndex, resultCol);

                    login.logout();

                } 
                
                else {
                    Assert.assertTrue(false);
                    xl.setCellData("Sheet1", rowIndex, resultCol, "FAIL");
                    xl.fillRedColor("Sheet1", rowIndex, resultCol);
                }
            }

            // INVALID CASE
             if (expected_status.equalsIgnoreCase("invalid")) {

                if (result) {
                    Assert.assertTrue(false);
                    xl.setCellData("Sheet1", rowIndex, resultCol, "FAIL");
                    xl.fillRedColor("Sheet1", rowIndex, resultCol);

                } else {
                    Assert.assertTrue(true);
                    xl.setCellData("Sheet1", rowIndex, resultCol, "PASS");
                    xl.fillGreenColor("Sheet1", rowIndex, resultCol);

                    login.logout();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();

            try {
                xl.setCellData("Sheet1", rowIndex, resultCol, "ERROR");
                xl.fillRedColor("Sheet1", rowIndex, resultCol);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}















// without aply color code 


//package testCases;
//
//import java.time.Duration;
//
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import org.testng.Assert;
//import org.testng.annotations.DataProvider;
//import org.testng.annotations.Test;
//
//import Utilities.dataProvider;
//import pageObjects.LoginPage;
//import testBase.baseClass_OrangeHRMS;
//
//public class Login_TC_DDT extends baseClass_OrangeHRMS{
//
//	LoginPage login;
//	@Test(dataProvider = "LoginDataTest", dataProviderClass = dataProvider.class)
//	
//
//	public void testlogincredentials(String username, String password,String expected_status) throws InterruptedException {
//		try {
//			login.setuser(username);
//			login.setpass(password);
//			login.submit();
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//		wait.until(ExpectedConditions.urlContains("dashboard"));
//
//
//		boolean result = driver.getCurrentUrl().contains("dashboard");
//
//
//		// Check for valid credentials
//		if(expected_status.equalsIgnoreCase("valid") ) {
//			if(result == true) {
//				Assert.assertTrue(true);	
//
//				System.out.println("User enter valid credentials! Login Successfull");
//				Thread.sleep(3000);
//				login.logout();
//				System.out.println("Logout successfully");
//				
//			}
//		}
//
//		else {
//			Assert.assertTrue(false);
//		}
//
//		// Check invalid credentiantial
//		if (expected_status.equalsIgnoreCase("invalid")) {
//			if(result == true) { // if user input invalid credential and login test fail
//				Assert.assertTrue(false);	
//
//				System.out.println("User enter invalid credentials! Login Successfull");
//				Thread.sleep(3000);
//				login.logout();
//				System.out.println("Logout successfully");
//			}
//
//		}
//		else { // if input is invalid and cannot login test pass 
//			Assert.assertTrue(true);
//		}
//
//	}
//		catch(Exception e) {
//			e.printStackTrace();
//		}
//			
//		}
//}
//

