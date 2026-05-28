package Utilities;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import drivers.DriverManager;

public class screenshot {
	// Capture screenshot on failure
	   public static String captureFailTC(String testName) {

	        String timestamp =
	                new SimpleDateFormat("yyyyMMdd_HHmmss")
	                .format(new Date());

	        String path =
	                System.getProperty("user.dir")
	                + "/bugs_screenshots/"
	                + testName + "_" + timestamp + ".png";

	        TakesScreenshot ts =
	                (TakesScreenshot) DriverManager.getDriver();

	        File source = ts.getScreenshotAs(OutputType.FILE);

	        File destination = new File(path);

	        try {

	            Files.copy(
	                    source.toPath(),
	                    destination.toPath(),
	                    StandardCopyOption.REPLACE_EXISTING);

	          System.out.println("Screenshot captured");

	        } catch (IOException e) {

	           System.out.println("Screenshot failed : " + e.getMessage());;
	        }

	        return path;
	    }
}
