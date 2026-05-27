package Utilities;

import java.io.IOException;
import org.testng.annotations.DataProvider;

public class dataProvider {

	@DataProvider(name = "LoginDataTest")
	public Object[][] getData() throws IOException {

		String path = System.getProperty("user.dir") + "/test_Data/login_credentials_testdata.xlsx";

		ExcelUtility xlutil = new ExcelUtility(path);

		int totalrows = xlutil.getRowCount("Sheet1");
		int totalcols = xlutil.getCellCount("Sheet1", 0);

		// +1 column for row index
		Object[][] logindata = new Object[totalrows][totalcols + 1];

		for (int i = 1; i <= totalrows; i++) {

			// store actual Excel row number
			logindata[i - 1][0] = i;

			for (int j = 0; j < totalcols; j++) {

				String value = xlutil.getCellData("Sheet1", i, j);
				if (value == null) value = "";

				logindata[i - 1][j + 1] = value;
			}
		}

		return logindata;
	}  
}

//@DataProvider(name = "LoginDataTest")
//public Object[][] getData() throws IOException {
//
//  String path = System.getProperty("user.dir") + "/test_Data/login_credentials_testdata.xlsx";
//
//  ExcelUtility xlutil = new ExcelUtility(path);
//
//  int totalrows = xlutil.getRowCount("Sheet1");
//  int totalcols = xlutil.getCellCount("Sheet1", 0);
// //  System.out.println("Rows: " + totalrows);
//  System.out.println("Cols: " + totalcols);
//
//  Object[][] logindata = new Object[totalrows][totalcols];
//
//  for (int i = 1; i <= totalrows; i++) {
//
//      for (int j = 0; j < totalcols; j++) {
//
//          String value = xlutil.getCellData("Sheet1", i, j);
//
//          if (value == null) {
//              value = "";
//          }
//
//          logindata[i - 1][j] = value;
//
//          System.out.println("Data [" + (i - 1) + "][" + j + "] = " + value);
//      }
//  }
//
//  return logindata;
//}