package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends basePage {

    // Constructor
    public LoginPage(WebDriver driver) {

        super(driver);
    }

       // Locators

    @FindBy(xpath = "//input[@placeholder='Username']")
    private WebElement username;

    @FindBy(xpath = "//input[@placeholder='Password']")
    private WebElement password;

    @FindBy(xpath = "//img[@alt='company-branding']")
    private WebElement comp_logo;

    @FindBy(xpath = "//button[normalize-space()='Login']")
    private WebElement submit_btn;

    @FindBy(xpath = "//p[@class='oxd-userdropdown-name']")
    private WebElement profile_link;

    @FindBy(xpath = "//a[normalize-space()='Logout']")
    private WebElement logout_link;

        // Action Methods
   
    public void setuser(String user) {

        logger.info("Entering username");

        try {

            username.clear();
            username.sendKeys(user);

            logger.info("Username entered successfully");

        } catch (Exception e) {

            logger.error("Failed to enter username : " + e.getMessage());

            throw e;
        }
    }

    public void setpass(String pwd) {

        logger.info("Entering password");

        try {

            password.clear();
            password.sendKeys(pwd);

            logger.info("Password entered successfully");

        } catch (Exception e) {

            logger.error("Failed to enter password : " + e.getMessage());

            throw e;
        }
    }

    public void submit() {
  
        logger.info("Clicking Login button");

        try {

            submit_btn.click();

            logger.info("Login button clicked successfully");

        } catch (Exception e) {

            logger.error("Failed to click Login button : " + e.getMessage());

            throw e;
        }
    }

    // Return Boolean instead of void
    public boolean display_logo() {

        logger.info("Checking company logo");

        try {

            boolean status = comp_logo.isDisplayed();

            if (status) {

                logger.info("Company logo displayed successfully");
            }

            return status;

        } catch (Exception e) {

            logger.error("Logo verification failed : " + e.getMessage());

            return false;
        }
    }

    public void logout() {

        logger.info("Performing Logout");

        try {

            profile_link.click();

            Thread.sleep(2000);

            logout_link.click();

            logger.info("Logout successful");

        } catch (Exception e) {

            logger.error("Logout failed : " + e.getMessage());

            throw new RuntimeException(e);
        }
    }
}