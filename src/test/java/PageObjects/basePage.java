package PageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
public class basePage {

	 protected WebDriver driver;   // protected so child classes can use it
	    protected Logger logger;      // logger accessible in child classes

	    // Constructor
	    public basePage(WebDriver driver) {
	        this.driver = driver;

	        // Logger initialized for child class dynamically
	        this.logger = LogManager.getLogger(this.getClass());

	        // Initialize PageFactory elements
	        PageFactory.initElements(driver, this);
	    }
	}

