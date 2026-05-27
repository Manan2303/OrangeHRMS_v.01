package TestCases;

import org.testng.annotations.Test;

import PageObjects.LoginPage;
import PageObjects.createVaccancy;
import TestBase.baseClass_HRMS;
import drivers.DriverManager;

public class createVacancy_TC extends baseClass_HRMS {

	LoginPage lp;
	createVaccancy vac;   

	@Test(groups = {"regression"})
	public void addVacancy_test() {

		// login page
   

		// access create Vacancy
		vac = new createVaccancy(DriverManager.getDriver());
		vac.recru_menu();
		vac.verifyPage();
		vac.input_details();
		vac.submit();

	}

}