package TestBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import drivers.DriverManager;

public class baseClass_HRMS {

	public WebDriver driver;
	public Logger logger;
	public Properties pr;

	@SuppressWarnings("deprecation")
	@Parameters({"browser","url", "os"})
	@BeforeClass(groups= {"sanity","regression","smoke"})
	public void setdata(String br, String URL, String os) throws IOException {

		// Load properties file (Relative Path) dynamic path
		FileReader file = new FileReader(System.getProperty("user.dir") +"/src/test/resources/property.properties");

		pr = new Properties();
		pr.load(file);

		// Logger setup
		logger = LogManager.getLogger(this.getClass());
		logger.info("***** Starting Test Execution ****");



		// Local environment Setup
		if(pr.getProperty("execution_env").equalsIgnoreCase("local"))
		{
			// Using DriverManager class
			//***************************

			  DriverManager.initDriver(br, os, URL);

	            driver = DriverManager.getDriver();

	            logger.info("Local Browser launched");


			// Without using Driver Managaer
			//********************************

			//			try {
			//
			//				switch (br.toLowerCase()) {
			//				case "chrome":
			//					WebDriverManager.chromedriver().setup();
			//					driver = new ChromeDriver();
			//					break;
			//
			//				case "edge":
			//					WebDriverManager.edgedriver().setup();
			//					driver = new EdgeDriver();
			//					break;
			//
			//				case "firefox":
			//					WebDriverManager.firefoxdriver().setup();
			//					driver = new FirefoxDriver();
			//					break;
			//
			//				default:
			//					logger.error("Invalid browser name: " + br);
			//					throw new IllegalArgumentException("Invalid browser");
			//				}
			//
			//
			//			} catch (Exception e) {
			//				logger.error("Browser setup failed", e);
			//				throw e;
			//			}
		}


		// Remote Environment
		if(pr.getProperty("execution_env").equalsIgnoreCase("remote")) {

			try {
				DriverManager.remoteDriver(os,br);

				driver = DriverManager.getDriver();

				

				System.out.println("Remote Execution using driverManager");
				logger.info("Remote browser launched");
				
			} catch (Exception e) {
				e.printStackTrace();
			}

					
			 // Open Application URL
			DriverManager.getDriver().get(URL);

	        logger.info("Application URL Opened");
	        logger.info("Current URL : " + DriverManager.getDriver().getCurrentUrl());
	    }
			//			try {
			//				DesiredCapabilities cap = new DesiredCapabilities();
			//				if(os.equalsIgnoreCase("Windows")) {
			//					cap.setPlatform(Platform.WIN10);
			//				}
			//				else if(os.equalsIgnoreCase("Mac")) {
			//					cap.setPlatform(Platform.MAC);
			//				}
			//				else if(os.equalsIgnoreCase("Linux")) {
			//					cap.setPlatform(Platform.LINUX);
			//				}
			//
			//				else {
			//					System.out.println("No matching operating system");
			//				}
			//
			//				// Browser
			//				switch (br.toLowerCase()) {
			//				case "chrome":
			//					cap.setBrowserName("chrome");
			//					break;
			//				case "edge":
			//					cap.setBrowserName("edge");
			//					break;
			//
			//				case "firefox":
			//					cap.setBrowserName("firefox");
			//					break;
			//				default:
			//					System.out.println("No browser matching");
			//					return;
			//				}
			//				driver = new RemoteWebDriver(new URL("http://localhost:4444"), cap);
			//
			//				driver.manage().deleteAllCookies();
			//				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
			//				driver.manage().window().maximize();
			//
			//				driver.get(URL);
			//				logger.info("Navigated to URL: " + URL);
			//				logger.info("Page Title: " + driver.getTitle());
			//
			//			}catch(Exception e) {
			//				logger.error("Browser setup failed", e);
			//				throw e;
			//			}

		}

	   @AfterClass(groups = {"sanity","regression","smoke"})
	    public void tearDown() {

	        logger.info("===== Closing Browser =====");

	        DriverManager.quitDriver();
	    }
	   
	
}















